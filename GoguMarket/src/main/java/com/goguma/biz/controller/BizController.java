package com.goguma.biz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BizController {
	
	@GetMapping("/shop04")
	public String shop04() {
		return "biz/shop04";
	}
	
	@GetMapping("/shop05")
	public String shop05() {
		return "biz/shop05";
	}
}
