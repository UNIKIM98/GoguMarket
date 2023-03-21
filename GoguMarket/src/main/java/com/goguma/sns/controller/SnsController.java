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
	// 단순 동네 생활 페이지 이동 관련 컨트롤러
	
	@Autowired
	SnsService service;

	@Autowired
	AtchService aservice;
	
	@GetMapping("/goguma/snsMain")
	public String SnsMain() {

		return "sns/snsMain";
	} // > 메인 페이지 > 동네 생활 
	
	@GetMapping("/my/mySns")
	public String mySns() {

		return "myPages/mySns";
	} // > 마이페이지 > 동네 생활  
	
	/*
	 * @GetMapping("/my/myReply") public String myReply() {
	 * 
	 * return "myPages/myReply"; }
	 */

	
	
	
	

}
