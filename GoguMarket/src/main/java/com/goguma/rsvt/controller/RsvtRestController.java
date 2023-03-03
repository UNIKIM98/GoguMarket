package com.goguma.rsvt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goguma.biz.service.BizMemService;

@RestController
public class RsvtRestController {
	
	@Autowired BizMemService memService; 	// 가게정보
	
}
