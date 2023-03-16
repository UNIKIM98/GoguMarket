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
import org.springframework.stereotype.Service;

import com.goguma.biz.service.BizMemService;
import com.goguma.biz.vo.BizMemVO;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.MemVO;

@Service
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	MemService memService;

	@Autowired
	BizMemService bizService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		MemVO memVO = (MemVO) auth.getPrincipal();
		System.out.println("일반로그인=======");

		HttpSession session = request.getSession();
		session.setAttribute("userId", memVO.getUserId()); // 아이디
		session.setAttribute("userSe", memVO.getUserSe()); // 권한
		session.setAttribute("nickNm", memVO.getNickNm()); // 닉네임
		session.setAttribute("dealArea", memVO.getDealArea()); // 거래지역
		session.setAttribute("atchPath", memVO.getAtchPath()); // 프로필사진 경로(img src에서 사용)
		session.setAttribute("mblTelno", memVO.getMblTelno()); // 전화번호
		session.setAttribute("userNm", memVO.getUserNm()); // 전화번호
		session.setAttribute("eml", memVO.getEml()); // 이메일

		if (memVO.getUserSe().equals("ROLE_BIZ")) {
			String bizId = memVO.getUserId();
			session.setAttribute("bizNo", bizService.selectBizNo(bizId));
		}
		if(memVO.getUserSe().equals("ROLE_ADMIN")) {
			response.sendRedirect("/admin/adminMember");
		}else {
			response.sendRedirect("/");
		}
	}

}
