package com.goguma.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.goguma.common.service.CommonCodeService;

@Controller
public class RptController {
	

	@GetMapping("/manageMain")
	public String manageMain() {

		return "manage/manageMain";
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
