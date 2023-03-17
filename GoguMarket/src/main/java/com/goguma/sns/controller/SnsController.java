package com.goguma.sns.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.goguma.common.service.AtchService;
import com.goguma.sns.service.SnsService;
import com.goguma.sns.vo.SnsVO;

@Controller
public class SnsController {
	@Autowired
	SnsService service;

	@Autowired
	AtchService aservice;
	
	@GetMapping("/sns/snsMain")
	public String SnsMain() {

		return "sns/snsMain";
	}

	@GetMapping("/my/mySns")
	public String mySns(Model model,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		String userId = (String)session.getAttribute("userId");
		
		System.out.println(userId);
		
		model.addAttribute("list", service.selectPerSns(userId));
	

		return "myPages/mySns";
	}
	
	
	

}
