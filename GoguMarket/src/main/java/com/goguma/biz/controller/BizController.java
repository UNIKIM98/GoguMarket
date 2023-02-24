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
	
	@GetMapping("/shop06")
	public String shop06() {
		return "biz/shop06";
	}
	
	@GetMapping("/shop0602")
	public String shop0602() {
		return "biz/shop0602";
	}
	
	@GetMapping("/shop07")
	public String shop07() {
		return "biz/shop07";
	}
	
	@GetMapping("/shop0702")
	public String shop0702() {
		return "biz/shop0702";
	}
	
	@GetMapping("/shop08")
	public String shop08() {
		return "biz/shop08";
	}
}
