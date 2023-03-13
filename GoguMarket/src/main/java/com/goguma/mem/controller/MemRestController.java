package com.goguma.mem.controller;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.goguma.UsersService;
import com.goguma.common.service.AtchService;
import com.goguma.mem.mail.EmailService;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.MemVO;

@RestController
public class MemRestController {
	// ■ Services ==========================================
	@Autowired
	private MemService memService;

	@Autowired
	private AtchService atchService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EmailService emailService;
	

	@Value("${goguma.save}")
	private String saveFolder;

	// ■ Handlers ============================
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

	// ======================================
	// ❤️ 이메일 중복 확인
	@GetMapping("/goguma/emlDupCkAjax/{eml}")
	public int emlDupCkAjax(@PathVariable String eml) {
		int chk = memService.isEmlCheck(eml);
		return chk;
	}

	// ======================================
	// ❤️ 이메일 인증 메일 전송
	@GetMapping("/goguma/emlCheckAJax/{eml}")
	public int emlCheckAJax(@PathVariable String eml) {
		// # 인증번호 난수 생성
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;

		String emailTtl = "[고구마켓] 이메일 인증번호가 도착했습니다.";
		// 이메일 전송
		try {
			emailService.sendVerificationMail(eml, checkNum, emailTtl);

		} catch (MessagingException e) {
			// 이메일 전송 실패 시
			System.out.println("[이메일 발송 실패] 에러발생 :( ");
			checkNum = 0;
		}
		return checkNum;
	}

	// ======================================
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

	// ======================================
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

	// ======================================
	// ❤️ 회원 정보 수정
	@PostMapping("/my/memUpdateFormSubmit")
	public int memUpdateFormSubmit(MemVO memVO, List<MultipartFile> files, HttpServletRequest request,
			Authentication auth) {
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

	// ======================================
	// ❤️ 프로필 사진 삭제
	@RequestMapping("/my/deleteProfile")
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

	// ======================================
	// ❤️ 아이디+이메일 일치 여부 확인
	@GetMapping("/goguma/checkIdEmlAjax/{userId}/{eml}")
	public boolean checkIdEmlAjax(@PathVariable String userId, @PathVariable String eml) {
		boolean result = false;
		System.out.println("넘어온 id > " + userId);
		System.out.println("넘어온 이메일 > " + eml);

		MemVO memVO = new MemVO();
		memVO.setUserId(userId);

		memVO = memService.selectUser(memVO);

		if (memVO != null && memVO.getEml() != null && memVO.getEml().equals(eml)) {
			result = true;
		}

		return result;
	}

	//
	@GetMapping("/goguma/isEmlExistAjax/{eml}")
	public String isEmlExistAjax(@PathVariable String eml) {
		System.out.println("넘어온  eml > " + eml);

		String result = "no";

		MemVO memVO = new MemVO();
		memVO.setEml(eml);

		memVO = memService.selectUser(memVO);

		System.out.println("찾아온 vo" + memVO);
		if (memVO != null && memVO.getEml() != null && memVO.getEml().equals(eml)) {
			result = memVO.getUserId();
		}
		System.out.println(result);
		return result;
	}
}