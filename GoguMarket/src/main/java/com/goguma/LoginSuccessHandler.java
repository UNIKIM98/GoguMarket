package com.goguma;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.MemVO;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	MemService memService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		System.out.println("=================================================");
		MemVO memVO = (MemVO) auth.getPrincipal();

		HttpSession session = request.getSession();
		session.setAttribute("userId", memVO.getUserId()); // 아이디
		session.setAttribute("userSe", memVO.getUserSe()); // 권한
		session.setAttribute("nickNm", memVO.getNickNm()); // 닉네임
		session.setAttribute("dealArea", memVO.getDealArea()); // 거래지역
		session.setAttribute("atchPath", memVO.getAtchPath()); // 프로필사진 경로(img src에서 사용)
		session.setAttribute("mblTelno", memVO.getMblTelno()); // 전화번호
		session.setAttribute("userNm", memVO.getUserNm()); // 전화번호
		session.setAttribute("eml", memVO.getEml()); // 전화번호

		response.sendRedirect("/goguma/dealMain");
	}

}
