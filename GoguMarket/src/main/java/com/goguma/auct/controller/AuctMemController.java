package com.goguma.auct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.goguma.auct.service.AuctMemService;
import com.goguma.auct.vo.AuctVO;

@Controller
public class AuctMemController {
	@Autowired
	AuctMemService auctMemService;
	
	@PostMapping("/auctSelect/{auctNo}")
	private String insertAuctMem(AuctVO vo) {
		// 입찰 등록
		vo.setUserId("user1");
		
		
		// 수정필요함!!
		auctMemService.insertAuctMem(null);
		return "auct/auctSelect/{auctNo}";
	}
}
