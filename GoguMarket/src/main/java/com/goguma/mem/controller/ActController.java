package com.goguma.mem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.goguma.mem.service.ActService;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.ActVO;
import com.goguma.mem.vo.MemVO;

@Controller
public class ActController {

	@Autowired
	ActService aServie;

	@Autowired
	MemService mService;

	// ▶ 계좌번호 창으로 가기(대표계좌 조회 + 계좌 리스트 조회)
	@GetMapping("/actInfo")
	public String auctSelect(Model model, HttpServletRequest request) {
		System.out.println("=======actInfo왔쓔===");
		MemVO mVO = new MemVO();
		// 0. 임시로그인
		HttpSession session = request.getSession();
		session.setAttribute("userId", "user1");

		// 1. 대표계좌번호 조회
		mVO.setUserId("user1");

		mVO = mService.selectUser(mVO);
		String actOne = null;

		if (mVO.getActNo() == null) {
			actOne = "no";
		} else {
			actOne = mVO.getActNo();
		}

		// ▷ 대표계좌 번호
		System.out.println(actOne);
		model.addAttribute("actOne", actOne);

		// ▷ 대표계좌 은행명
		System.out.println(mVO.getBankNm());
		model.addAttribute("bankNmOne", mVO.getBankNm());

		// 2. 계좌번호 리스트 조회
//		model.addAttribute("bankList", aServie.getActList("user1"));
//		System.out.println(aServie.getActList("user1").getClass().getName());

		List<ActVO> bankList = aServie.getActList("user1");
		System.out.println(bankList + "=========" + bankList.size());
		if (bankList.size() != 0) {
			model.addAttribute("bankList", bankList);
		} else {
			model.addAttribute("bankList", "no");
		}

		return "myPages/actInfo";
	}

	// ▶ 대표 계좌번호 삭제 : AuctRestController

	// ==============================
	// ▶ 계좌번호 리스트 조회(최대 3개)

	// ▶ 계좌번호 리스트에 추가

	// ▶ 계좌번호 리스트에서 삭제

}
