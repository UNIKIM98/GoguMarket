package com.goguma;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

@Service
public class FailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String message = "⚠️오류로 인하여 로그인이 정상적으로 처리되지 않았습니다. 다시 시도해주세요.";

		// 정지된 회원
		if (exception instanceof DisabledException) {
			message = "⚠️관리자에 의해 정지된 계정입니다.";
		} 

		// 유효하지 않은 아이디/비밀번호
		else if (exception instanceof BadCredentialsException) {
			message = "⚠️아이디 혹은 비밀번호가 일치하지 않습니다.";
		}

		// 존재하지 않는 아이디
		else if(exception instanceof UsernameNotFoundException) {
			message = "⚠️존재하지 않는 회원입니다.";
		}

		// 인증 과정에서 오류 발생
		else if(exception instanceof InternalAuthenticationServiceException) {
			message = "⚠️인증 도중 오류가 발생하였습니다. 다시 시도해주세요.";
		}

		out.println("<script language='javascript'>");
		out.println("alert('" + message + "'); location.href='/goguma/login';");
		out.println("</script>");
		out.flush();

	}

}
