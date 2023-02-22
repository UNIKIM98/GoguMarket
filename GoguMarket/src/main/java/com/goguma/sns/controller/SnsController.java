package com.goguma.sns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SnsController {

	@GetMapping("/snsMain")
	public String SnsMain() {
		return "sns/snsMain";
	}
	
	
	
}
