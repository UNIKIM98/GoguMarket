package com.goguma.mem.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.goguma.mem.service.ActService;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.ActVO;

@Controller
public class ActController {

	@Autowired
	ActService actServie;

	@Autowired
	MemService memService;

	// ▶ 계좌번호 창으로 가기(대표계좌 조회 + 계좌 리스트 조회)
	@GetMapping("/myAct")
	public String auctSelect(HttpServletRequest request) {
		System.out.println("=======/myAct");
		
		// ※ 임시로그인
		HttpSession session = request.getSession();
		session.setAttribute("userId", "user1");

		return "myPages/myAct";
	}

	@PostMapping("/makeNewActSubmit")
	public String makeNewActSubmit(ActVO actVO, HttpServletResponse response) {
		System.out.println("actVO ==> "+ actVO);
		int cnt = actServie.insertAct(actVO);
		
		
		try {
			if (cnt > 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.println("<script language='javascript'>");
				out.println("alert('[계좌등록완료]"+actVO.getBankNm()+"은행 계좌 등록이 완료되었습니다.');location.href='/myAct';");
				// ※ 메인페이지로 가게 고쳐야함!!

				out.println("</script>");

				out.flush();

			} else {
				response.setContentType("text/html; charset=UTF-8");

				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('[계좌등록실패] 나의 계좌 목록으로 이동합니다.');location.href='/myAct';");
																									
				out.println("</script>");

				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}

}
