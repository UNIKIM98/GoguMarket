package com.goguma.manage.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemManController {
	

	@GetMapping("/memberMange")
	public String memberManage() {

		return "manage/memberManage";
	}

}
