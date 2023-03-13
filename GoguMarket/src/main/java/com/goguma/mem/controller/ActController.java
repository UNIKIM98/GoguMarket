package com.goguma.mem.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goguma.mem.service.ActService;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.ActVO;

@Controller
public class ActController {

	@Autowired
	ActService actServie;

	@Autowired
	MemService memService;
	// ===========================================================
	// ❤️ 계좌번호 창으로 가기(대표계좌 조회 + 계좌 리스트 조회)
	@GetMapping("/my/myAct")
	public String auctSelect() {
		return "myPages/myAct";
	}
	
	// ===========================================================	
	// ❤️ 계좌 등록
	@RequestMapping("/my/makeNewAct")
	public String makeNewAct() {
		return "myPages/makeNewAct";
	}
	
	// ===========================================================	
	// ❤️ 계좌 등록 submit
	@PostMapping("/my/makeNewActSubmit")
	public void makeNewActSubmit(ActVO actVO, HttpServletResponse response) {
		System.out.println("actVO ==> "+ actVO);
		int cnt = actServie.insertAct(actVO);
		
		
		try {
			if (cnt > 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.println("<script language='javascript'>");
				out.println("alert('[계좌등록완료]"+actVO.getBankNm()+"은행 계좌 등록이 완료되었습니다.');location.href='/my/myAct';");
				out.println("</script>");

				out.flush();

			} else {
				response.setContentType("text/html; charset=UTF-8");

				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('[계좌등록실패] 나의 계좌 목록으로 이동합니다.');location.href='/my/myAct';");
																									
				out.println("</script>");

				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
