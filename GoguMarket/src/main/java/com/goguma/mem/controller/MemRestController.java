package com.goguma.mem.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.goguma.mem.service.AttendService;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.AttendVO;
import com.goguma.mem.vo.MemVO;

@RestController
public class MemRestController {
	@Autowired
	MemService mService;
	
	/*
	 * @Autowired AttendService aService;
	 */

	// 아이디체크
	@GetMapping("/userIdChk/{userId}")
	public String userIdChk(@PathVariable String userId) {
		// 있으면 1 없으면 0
		int chk = mService.isIdCheck(userId);
		String result = "1";
		if (chk == 0) {
			result = "0";
		}

		return result;
	}

	// 닉네임 체크
	@GetMapping("/nickNmChk/{nickNm}")
	public String nickNmChk(@PathVariable String nickNm) {
		int chk = mService.isNickNmCheck(nickNm);
		String result = "1";

		if (chk == 0) {
			result = "0";
		}

		return result;
	}

	// 거래지역 체크
	@PostMapping("/myAreaSetAjax")
	public String myAreaSetAjax(@RequestBody MemVO vo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		vo.setUserId(userId);
		String result = "fail";

		int cnt = mService.updateDealArea(vo);

		if (cnt > 0) {
			result = "success";
		}
		return result;
	}

	// 출석이벤트
	@GetMapping("/ajaxAttend/{attendYmd}")
	public String ajaxAttend(@PathVariable Date attendYmd, HttpServletRequest request) {
		System.out.println(attendYmd);
		
		HttpSession session = request.getSession();
		
		AttendVO aVO = new AttendVO();
		aVO.setUserId((String)session.getAttribute("userId"));
		aVO.setAttendYmd(attendYmd);
		
		return "hihi";
	}
}
