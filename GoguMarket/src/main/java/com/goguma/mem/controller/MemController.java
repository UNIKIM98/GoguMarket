package com.goguma.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goguma.common.service.AtchService;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.MemVO;

@Controller
public class MemController {
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	MemService memService;

	@Autowired
	AtchService atchService;

	@GetMapping("/myPageTest")
	public String myPageTest() {
		return "myPages/test";
	}
	// ===========================================================
	// ▷ 회원 로그인
	@GetMapping("/goguma/login")
	public String login() {
		return "mem/login";
	}
	
	//
	// ▷ 로그아웃
//	@GetMapping("/goguma/logout")
//	public String logout() {
//		return "/logout";
//	}
	// ===========================================================
	// ▷ 일반 회원가입
	@GetMapping("/goguma/memberJoinForm")
	public String memberJoinForm() {
		return "mem/memberJoinForm";
	}

	@PostMapping("/goguma/memberJoin")
	public String memberJoin(MemVO mVO, HttpServletResponse response) {
		mVO.setUserSe("USER"); // ※ 일반회원 > 공통코드 사용해야하는 거 아닌감
		mVO.setUserStts("0"); // ※ 정상 > 공통코드 사용해야하는 거 아닌감
		System.out.println(mVO);

		// ※ 비밀번호 암호화하기
		 String userPw = mVO.getUserPw();
		userPw = bCryptPasswordEncoder.encode(userPw);
		
		mVO.setUserPw(userPw);
		
		int cnt = memService.memberJoin(mVO);

		try {
			if (cnt > 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.println("<script language='javascript'>");
				out.println("alert('[회원가입성공] " + mVO.getUserNm() + "님 환영합니다 :D '); location.href='/auctList';");
				// ※ 메인페이지로 가게 고쳐야함!!

				out.println("</script>");

				out.flush();

			} else {
				response.setContentType("text/html; charset=UTF-8");

				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('[회원가입실패] 회원가입 페이지로 이동합니다.');location.href='/memberJoinForm';"); // ※ 메인페이지로 가게
																									// 고쳐야함!!
				out.println("</script>");

				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "mem/memberJoinForm"; // ※ 메인페이지로 가게 고쳐야함!!
	}
	
	// ===========================================================
	// ▷ 소셜회원가입

	@GetMapping("/test")
	public String test() {
		return "myPages/test";
	}

	// ===========================================================
	// ▷ 내 쿠폰/포인트
	@GetMapping("/my/myCouponNPoint")
	public String myCouponNPoint() {
		return "myPages/myCouponNPoint";
	}

	// ===========================================================
	// ▷ 우리동네 설정
	@GetMapping("/my/myArea")
	public String myArea(HttpServletRequest request, MemVO mVO, Model model) {
		HttpSession session = request.getSession();
		mVO.setUserId((String) session.getAttribute("userId"));

		model.addAttribute("userInfo", memService.selectUser(mVO));
		System.out.println(memService.selectUser(mVO));

		return "myPages/myArea";
	}

	// ===========================================================
	// ▷ 회원정보 수정
	@GetMapping("/my/myInfoCheck")
	public String myInfoCheck(HttpServletRequest request, MemVO mVO, Model model) {
		HttpSession session = request.getSession();
		mVO.setUserId((String) session.getAttribute("userId"));
		
		return "myPages/myInfoCheck";
	}
	
	
	@GetMapping("/my/myInfo")
	public String myInfo(HttpServletRequest request, MemVO mVO, Model model) {
		HttpSession session = request.getSession();
		
		mVO.setUserId((String) session.getAttribute("userId"));
		mVO = memService.selectUser(mVO);
		model.addAttribute("userInfo", mVO);
		
		System.out.println(mVO);

		return "myPages/myInfo";
	}

	// ===========================================================
	// ▷ 회원정보 수정
	@RequestMapping("/my/makeNewAct")
	public String makeNewAct(){
		return "myPages/makeNewAct";
	}
}
