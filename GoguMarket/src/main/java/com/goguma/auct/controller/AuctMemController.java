package com.goguma.auct.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.goguma.auct.service.AuctMemService;
import com.goguma.auct.service.AuctService;
import com.goguma.auct.vo.AuctMemVO;
import com.goguma.auct.vo.AuctVO;

@Controller
public class AuctMemController {
	@Autowired
	AuctMemService auctMemService;
	@Autowired
	AuctService auctService;
	// ▼경매물품 입찰
	@PostMapping("/my/insertAuctMem")
	private String insertAuctMem(AuctMemVO vo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String myId = (String) session.getAttribute("userId"); //세션값중 userId를 받음
		vo.setUserId(myId); //세션값으로 아이디 설정
		
		auctMemService.insertAuctMem(vo); // 입찰서비스
		
		return "redirect:/goguma/auctSelect/"+vo.getAuctNo(); //완료시 단건조회페이지로
	}
	// ▼입찰한 경매품 조회
	@GetMapping("/my/bidAuction")
	private String bidAuction(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		List<AuctMemVO> mybidList= auctMemService.bidAuction(userId); //세션값 중 userId로 입찰내역 서비스 실행
		
		model.addAttribute("mybidList",mybidList); //모델에 담기
		return "auction/bidAuction";
		
	}

	
}
