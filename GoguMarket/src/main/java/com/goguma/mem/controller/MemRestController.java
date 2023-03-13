package com.goguma.mem.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.goguma.common.service.AtchService;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.MemVO;

@RestController
public class MemRestController {
	@Autowired
	MemService memService;

	@Autowired
	AtchService atchService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/*
	 * @Autowired private AuthenticationManager authenticationManager;
	 */

	@Value("${goguma.save}")
	private String saveFolder;

	// ❤️ 아이디체크
	@GetMapping("/goguma/userIdChk/{userId}")
	public String userIdChk(@PathVariable String userId) {
		// 있으면 1 없으면 0
		int chk = memService.isIdCheck(userId);
		String result = "1";
		if (chk == 0) {
			result = "0";
		}

		return result;
	}

	// ❤️ 닉네임 체크
	@GetMapping("/goguma/nickNmChk/{nickNm}")
	public String nickNmChk(@PathVariable String nickNm) {
		int chk = memService.isNickNmCheck(nickNm);
		String result = "1";

		if (chk == 0) {
			result = "0";
		}

		return result;
	}

	// ❤️ 거래지역 설정
	@PostMapping("/my/myAreaSetAjax")
	public String myAreaSetAjax(@RequestBody MemVO vo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		vo.setUserId(userId);
		String result = "fail";

		int cnt = memService.updateDealArea(vo);

		if (cnt > 0) {
			result = "success";
		}
		return result;
	}

	// ❤️ 회원 정보 수정
	@PostMapping("/my/memUpdateFormSubmit")
	public int memUpdateFormSubmit(MemVO memVO, List<MultipartFile> files, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		// 프로필사진 있으면 실행
		if (files != null && !files.isEmpty()) {
			for (MultipartFile file : files) {
				if (file.getSize() == 0)
					continue;

				String fileName = UUID.randomUUID().toString();
				fileName = fileName + "_" + file.getOriginalFilename();

				File uploadFile = new File(saveFolder, fileName);
				try {
					file.transferTo(uploadFile);

				} catch (Exception e) {
					e.printStackTrace();
				}

				memVO.setAtchNm(fileName);
				memVO.setAtchPath("/upload/" + fileName);
				session.setAttribute("atchPath", session);
			}

		// 없으면
		} else {
			memVO.setAtchNm(null);
			memVO.setAtchPath(null);
		}
		System.out.println("바꾼 VO => " + memVO);
		String userPw = memVO.getUserPw();
		userPw = bCryptPasswordEncoder.encode(userPw);
		memVO.setUserPw(userPw);

		int memUpdateCnt = memService.updateUser(memVO);

		return memUpdateCnt;
	}

	@RequestMapping("/my/deleteProfile")
	@ResponseBody
	public int deleteProfile(@RequestBody String userId) {
		int cnt = 0;

		MemVO mVO = new MemVO();
		mVO.setUserId(userId);
		mVO = memService.selectUser(mVO);

		File file = new File(saveFolder + mVO.getAtchNm());
		boolean result = file.delete();
		// 파일 삭제시 1 삭제 실패시 0
		if (result) {
			cnt = 1;
		}
		System.out.println(cnt + "삭제했으면1");
		return cnt;
	}

}
