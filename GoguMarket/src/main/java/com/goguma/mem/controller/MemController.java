package com.goguma.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	// ❤️ 회원 로그인
	@GetMapping("/goguma/login")
	public String login() {
		return "mem/login";
	}
	
	// ===========================================================
	// ❤️ 회원 로그아웃
	@GetMapping("/goguma/logout")
	public void logout(HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<script language='javascript'>");
		out.println("alert('👋 정상적으로 로그아웃되었습니다.'); location.href='/';");
		out.println("</script>");

		out.flush();
	}

	// ===========================================================
	// ❤️ 일반 회원가입
	// 회원가입페이지 이동
	@GetMapping("/goguma/memberJoinForm")
	public String memberJoinForm() {
		return "mem/memberJoinForm";
	}

	// 회원가입 폼 submit
	@PostMapping("/goguma/memberJoin")
	public void memberJoin(MemVO mVO, HttpServletResponse response) {
		mVO.setUserSe("USER"); // ※ 일반회원 > 공통코드 사용해야하는 거 아닌감
		mVO.setUserStts("0"); // ※ 정상 > 공통코드 
		// 비밀번호 암호화하기
		String userPw = mVO.getUserPw();
		userPw = bCryptPasswordEncoder.encode(userPw);

		mVO.setUserPw(userPw);

		int cnt = memService.memberJoin(mVO);

		try {
			if (cnt > 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.println("<script language='javascript'>");
				out.println("alert('[회원가입성공] " + mVO.getUserNm() + "님 환영합니다 :D '); location.href='/';");
				out.println("</script>");

				out.flush();

			} else {
				response.setContentType("text/html; charset=UTF-8");

				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('[회원가입실패] 다시 시도해주세요 :(');location.href='redirect:/goguma/memberJoinForm';"); // ※ 메인페이지로 가게
																										// 고쳐야함!!
				out.println("</script>");

				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ===========================================================
	// ❤️ 소셜회원가입

	@GetMapping("/test")
	public String test() {
		return "myPages/test";
	}

	// ===========================================================
	// ❤️ 우리동네 설정
	@GetMapping("/my/myArea")
	public String myArea(HttpServletRequest request, MemVO mVO, Model model){
		HttpSession session = request.getSession();
		mVO.setUserId((String) session.getAttribute("userId"));

		model.addAttribute("userInfo", memService.selectUser(mVO));

		return "myPages/myArea";
	}

	// ===========================================================
	// ❤️ 회원정보 수정
	@GetMapping("/my/myInfoCheck/{value}")
	public String myInfoCheck(@PathVariable String value, Model model) {
		model.addAttribute("pwCkPage", value);
		return "myPages/myInfoCheck";
	}
	
	// ===========================================================
	// ❤️ 회원정보 수정 Ajax
	@PostMapping("/my/myPwCh")
	public String myPwCh(HttpServletRequest request, MemVO checkVO, Model model, HttpServletResponse response) {
		HttpSession session = request.getSession();

		// 비교할 memVO
		MemVO mVO = new MemVO();
		mVO.setUserId((String) session.getAttribute("userId"));
		mVO = memService.selectUser(mVO);

		// 받은 pw 암호화하기
		String userPw = checkVO.getUserPw();

		// 돌아갈 페이지 설정
		String page = "myPages/myInfoCheck";
		try {
			if (bCryptPasswordEncoder.matches(userPw, mVO.getUserPw())) {
				// model에 유저 정보 담아주고
				model.addAttribute("userInfo", mVO);

				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.flush();
				if(checkVO.getPwCkPage().equals("info")) {
					page = "myPages/myInfo";					
				}else if(checkVO.getPwCkPage().equals("goodBye")) {
					page = "myPages/goodBye";					
				}
			} else {
				response.setContentType("text/html; charset=UTF-8");

				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('비밀번호가 일치하지 않습니다 :(');");
				out.println("</script>");

				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;
	}

	// ===========================================================
	// ❤️ 비밀번호 찾기
	@GetMapping("/goguma/findPw")
	public String findPw() {
		return "mem/findPw";
	}

	// ===========================================================
	// ❤️ 비밀번호 수정
	@PostMapping("/goguma/updatePw")
	public String updatePw(MemVO mVO, HttpServletResponse response) {

		String userPw = mVO.getUserPw();
		userPw = bCryptPasswordEncoder.encode(userPw);
		mVO.setUserPw(userPw);

		int cnt = memService.updateUserPw(mVO);
		try {
			if (cnt > 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.println("<script language='javascript'>");
				out.println("alert('비밀번호를 성공적으로 변경하였습니다. 로그인을 해주세요'); location.href='/goguma/login';");
				// ※ 메인페이지로 가게 고쳐야함!!

				out.println("</script>");

				out.flush();

			} else {
				response.setContentType("text/html; charset=UTF-8");

				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('[비밀번호 변경 실패] 다시 시도해주세요 :(');location.href='/goguma/login';"); // ※ 메인페이지로 가게
																									// 고쳐야함!!
				out.println("</script>");

				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "myPages/myInfo";
	}

	// ===========================================================
	// ❤️ 아이디 찾기
	@GetMapping("/goguma/findId")
	public String findId() {
		return "mem/findId";
	}
	
	// ===========================================================
	// ❤️ 회원 탈퇴
	@GetMapping("/my/goodbye")
	public String goodBye() {
		return "myPages/goodBye";
	}
	// ===========================================================
	// ❤️ 회원 탈퇴 Ajax
	@GetMapping("/my/goodByeAjax")
	public void goodByeAjax(HttpSession session, MemVO vo) {
		String userId = (String)session.getAttribute("userId");
		vo.setUserId(userId);
		memService.deleteMember(vo);
		SecurityContextHolder.clearContext();
		
		session.removeAttribute("userId");
		session.removeAttribute("userSe"); // 권한
		session.removeAttribute("nickNm"); // 닉네임
		session.removeAttribute("dealArea"); // 거래지역
		session.removeAttribute("atchPath"); // 프로필사진 경로(img src에서 사용)
		session.removeAttribute("mblTelno"); // 전화번호
		session.removeAttribute("userNm"); // 전화번호
		session.removeAttribute("eml"); // 이메일
		
		
		
		
	}

}
