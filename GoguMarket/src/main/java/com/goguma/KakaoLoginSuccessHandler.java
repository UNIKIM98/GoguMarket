package com.goguma;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.goguma.common.service.AlarmService;
import com.goguma.common.vo.AlarmVO;
import com.goguma.mem.service.MemService;
import com.goguma.mem.socialLogin.OAuthAttributes;
import com.goguma.mem.vo.MemVO;

public class KakaoLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		MemVO mvo = (MemVO) session.getAttribute("user");

		session.setAttribute("userId", mvo.getUserId()); // 아이디
		session.setAttribute("userSe", "ROLE_USER"); // 권한
		session.setAttribute("nickNm", mvo.getNickNm()); // 닉네임
		session.setAttribute("atchPath", mvo.getAtchPath()); // 프로필사진 경로(img src에서 사용)
		session.setAttribute("userNm", mvo.getUserNm()); // 이름
		session.setAttribute("eml", mvo.getEml()); // 이메일

		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();

			out.println("<script language='javascript'>");
			out.println("alert('" + mvo.getNickNm() + "님 환영합니다 :D '); location.href='/';");
			out.println("</script>");

			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// response.sendRedirect("/"); // location 걸어주기 때문에 필요 없음!
	}

}
