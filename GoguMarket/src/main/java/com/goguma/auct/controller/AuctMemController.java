package com.goguma.auct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.goguma.auct.service.AuctMemService;

@Controller
public class AuctMemController {
	@Autowired
	AuctMemService auctMemService;
	
	@PostMapping("/auctSelect/{auctNo}")
	private String insertAuctMem() {
		// 입찰 등록
		return "auct/auctSelect/{auctNo}";
	}
}
