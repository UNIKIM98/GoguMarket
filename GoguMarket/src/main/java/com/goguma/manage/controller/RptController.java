package com.goguma.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RptController {
	

	@GetMapping("/manageMain")
	public String manageMain() {
		return "manage/manageMain";
	}
	
	
	@GetMapping("/memberManage")
	public String memberManage() {
		return "manage/memberManage";
	}
	
	@GetMapping("/boardManage")
	public String boardManage() {
		return "manage/boardManage";
	}
	
	@GetMapping("/reportManage")
	public String reportManage() {
		return "manage/reportManage";
	}
	
	
	
	

}
