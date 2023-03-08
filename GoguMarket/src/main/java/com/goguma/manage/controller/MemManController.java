package com.goguma.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.goguma.common.service.CommonCodeService;
import com.goguma.mem.service.MemService;

@Controller
public class MemManController {
	@Autowired
	CommonCodeService common;

	@Autowired
	MemService member;
	
	@GetMapping("/memberManage")
	public String memberManage() {
		
		common.codeList("004");
		

		return "manage/memberManage";
	}

}
