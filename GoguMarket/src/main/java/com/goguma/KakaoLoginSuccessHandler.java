package com.goguma;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.goguma.mem.service.MemService;
import com.goguma.mem.socialLogin.OAuthAttributes;
import com.goguma.mem.vo.MemVO;

public class KakaoLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		System.out.println("kakaoLoginSuccessHandler");
		System.out.println(auth);
		System.out.println(auth.getPrincipal());

		HttpSession session = request.getSession();
		MemVO mvo = (MemVO) session.getAttribute("user");
		
		session.setAttribute("userId", mvo.getUserId()); // 아이디
		session.setAttribute("userSe", "ROLE_USER"); // 권한
		session.setAttribute("nickNm", mvo.getNickNm()); // 닉네임
		session.setAttribute("atchPath", mvo.getAtchPath()); // 프로필사진 경로(img src에서 사용)
		session.setAttribute("userNm", mvo.getUserNm()); // 이름
		session.setAttribute("eml", mvo.getEml()); // 이메일
		
		response.sendRedirect("/");
	}

}
