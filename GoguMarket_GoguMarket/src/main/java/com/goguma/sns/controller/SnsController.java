package com.goguma.sns.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		  HttpSession session = request.getSession();
		  Map<String,Object>map = new HashMap<String,Object>();
		  
	      session.setAttribute("userId", "user1");
	      session.setAttribute("area", "Pohang");
	      
	      session.getAttribute("userId");
	      session.getAttribute("area");
	      
	    
	      
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
