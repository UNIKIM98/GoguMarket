package com.goguma.auct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuctController {

	@GetMapping("/auctSelectList")
	public String auctSelectList() {
		//효근
		return "auction/auctSelectList";
	}
	
	@GetMapping("/auctSelect")
	public String auctSelect() {
		//효근
		return "auction/auctSelect";
	}
}
