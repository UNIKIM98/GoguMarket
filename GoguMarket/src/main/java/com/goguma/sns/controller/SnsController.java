package com.goguma.sns.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class SnsController {

	@GetMapping("/snsMain")
	public String SnsMain() {
		return "sns/snsMain";
	}
	
	
	
}
