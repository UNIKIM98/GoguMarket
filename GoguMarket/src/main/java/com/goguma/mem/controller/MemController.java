package com.goguma.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goguma.common.service.AtchService;
import com.goguma.common.service.CommonCodeService;
import com.goguma.common.vo.AtchVO;
import com.goguma.deal.service.DealService;
import com.goguma.deal.vo.DealVO;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.MemVO;

@Controller
public class MemController {

	@Autowired
	MemService mService;

	@Autowired
	AtchService aService;

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
		return "myPages/test";
	}

	// ▶ 내 쿠폰/포인트 ===========================================================
	@GetMapping("/myCouponNPoint")
	public String myCouponNPoint() {
		return "myPages/myCouponNPoint";
	}

	// ▶ 우리 동네 설정 ===========================================================
	@GetMapping("/myArea")
	public String myArea(HttpServletRequest request, MemVO mVO, Model model) {
		HttpSession session = request.getSession();
		mVO.setUserId((String) session.getAttribute("userId"));

		model.addAttribute("userInfo", mService.selectUser(mVO));
		System.out.println(mService.selectUser(mVO));

		return "myPages/myArea";
	}

	
}
