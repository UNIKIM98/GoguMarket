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
import org.springframework.stereotype.Service;

import com.goguma.biz.service.BizMemService;
import com.goguma.common.service.AlarmService;
import com.goguma.common.vo.AlarmVO;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.MemVO;

@Service
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	MemService memService;

	@Autowired
	BizMemService bizService;
	
	@Autowired
	AlarmService alarmService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		
		//세션에 필요한 값 담기
		MemVO memVO = (MemVO) auth.getPrincipal();

		HttpSession session = request.getSession();
		session.setAttribute("userId", memVO.getUserId()); // 아이디
		session.setAttribute("userSe", memVO.getUserSe()); // 권한
		session.setAttribute("nickNm", memVO.getNickNm()); // 닉네임
		session.setAttribute("dealArea", memVO.getDealArea()); // 거래지역
		session.setAttribute("addr", memVO.getAddr());//주소
		session.setAttribute("atchPath", memVO.getAtchPath()); // 프로필사진 경로(img src에서 사용)
		session.setAttribute("mblTelno", memVO.getMblTelno()); // 전화번호
		session.setAttribute("userNm", memVO.getUserNm()); // 이름
		session.setAttribute("eml", memVO.getEml()); // 이메일

		// 알림 담기
		AlarmVO aVO = new AlarmVO();
		aVO.setUserId(memVO.getUserId());
		
		int alarmCount = alarmService.checkNotifyCount(aVO);
		session.setAttribute("alarm", alarmCount);
		
		//비즈회원 > 비즈번호 담기
		if (memVO.getUserSe().equals("ROLE_BIZ")) {
			String bizId = memVO.getUserId();
			session.setAttribute("bizNo", bizService.selectBizNo(bizId));
		}
		
		// location할 페이지 설정
		String page = null;
		if(memVO.getUserSe().equals("ROLE_ADMIN")) {
			page = "/admin/adminMember";
		}else {
			page = "/";
		}
		
		// alert + location
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();

			out.println("<script language='javascript'>");
			out.println("alert('" + memVO.getNickNm() + "님 안녕하세요 :D '); location.href='"+page+"';");
			out.println("</script>");

			out.flush();}catch (IOException e) {
				e.printStackTrace();
			}
	}

}
