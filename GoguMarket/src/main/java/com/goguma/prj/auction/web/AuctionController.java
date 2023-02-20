package com.goguma.prj.auction.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuctionController {
	
	@GetMapping("/auction")
	public String auction() {
		return "auction/auction";
	}
	
}
