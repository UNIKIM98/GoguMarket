package com.goguma.sns.controller;




import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.goguma.sns.service.SnsService;

@Controller
public class SnsController {
	@Autowired
	SnsService service;

	@GetMapping("/snsMain")
	public String SnsMain(HttpServletRequest request) {

		return "sns/snsMain";
	}

	@GetMapping("/replyView")
	public String replyView() {
		return "sns/replyView";
	}

	@GetMapping("/replyWrite")
	public String replyWrite() {
		return "sns/replyWrite";
	}

}
