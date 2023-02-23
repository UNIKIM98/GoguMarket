package com.goguma.mem.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.MemVO;


@Controller
public class MemController {

	@Autowired
	MemService mService;


	@GetMapping("/myPageTest")
	public String myPageTest() {
		return "myPages/test";
	}

	// ▶ 일반 회원가입 ===========================================================
	// 회원가입 폼
	@GetMapping("/memberJoinForm")
	public String memberJoinForm() {
		return "mem/memberJoinForm";
	}

	// 전화번호 인증
	public String mblTelNoChk() {

		return "";
	}
	// 계좌번호 인증

	// 주소 입력

	// 회원가입하기
	@PostMapping("/memberJoin.do")
	public String memberJoin(MemVO mVO, HttpServletResponse response) {
		mVO.setUserSe("1"); // ※ 일반회원 > 공통코드 사용해야하는 거 아닌감
		mVO.setUserStts("1"); // ※ 정상 > 공통코드 사용해야하는 거 아닌감

		// ※ 비밀번호 암호화하기
		String userPw = mVO.getUserPw();

		int cnt = mService.memberJoin(mVO);

		try {
			if (cnt > 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.println("<script language='javascript'>");
				out.println("alert('[회원가입성공] " + mVO.getUserNm() + "님 환영합니다 :D ');location.href='memberJoinForm';");
				// ※ 메인페이지로 가게 고쳐야함!!

				out.println("</script>");

				out.flush();

			} else {
				response.setContentType("text/html; charset=UTF-8");

				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('[회원가입실패] 회원가입 페이지로 이동합니다.');location.href='memberJoinForm.do';"); // ※ 메인페이지로 가게
																										// 고쳐야함!!
				out.println("</script>");

				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "mem/memberJoinForm"; // ※ 메인페이지로 가게 고쳐야함!!
	}

	// ▶ 소셜 회원가입 ===========================================================

	@GetMapping("/test")
	public String test() {
		return "test";
	}
}
