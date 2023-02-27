package com.goguma.sns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.goguma.sns.service.SnsService;
import com.goguma.sns.vo.SnsVO;

@Controller
public class SnsController {
	@Autowired
	SnsService service;

	@GetMapping("/snsMain")
	public String SnsMain() {
		return "sns/snsMain";
	}

	
}
