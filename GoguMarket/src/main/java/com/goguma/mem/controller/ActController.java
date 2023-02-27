package com.goguma.mem.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.goguma.mem.service.ActService;
import com.goguma.mem.service.MemService;

@Controller
public class ActController {

	@Autowired
	ActService aServie;

	@Autowired
	MemService mService;

	// ▶ 계좌번호 창으로 가기(대표계좌 조회 + 계좌 리스트 조회)
	@GetMapping("/myAct")
	public String auctSelect(HttpServletRequest request) {
		System.out.println("=======/myAct");
		
		// ※ 임시로그인
		HttpSession session = request.getSession();
		session.setAttribute("userId", "user1");

		return "myPages/myAct";
	}

	// ▶ 대표 계좌번호 삭제 : AuctRestController

	// ==============================
	// ▶ 계좌번호 리스트 조회(최대 3개)

	// ▶ 계좌번호 리스트에 추가

	// ▶ 계좌번호 리스트에서 삭제

}
