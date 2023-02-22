package com.goguma.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RptController {
	@GetMapping("/report")
	public String Report() {
		return "manage/report";
	}

	@GetMapping("/manageMain")
	public String manageMain() {
		return "manage/manageMain";
	}

}
