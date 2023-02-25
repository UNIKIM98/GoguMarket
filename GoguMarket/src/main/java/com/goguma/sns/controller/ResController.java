package com.goguma.sns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goguma.sns.service.SnsService;
import com.goguma.sns.vo.SnsVO;

@RestController
public class ResController {
	@Autowired
	SnsService service;
	
	@GetMapping("/getSnsList")
	public List<SnsVO> getSnsList() {
		System.out.println("sda");

		List<SnsVO> result = service.getSnsList();

		return result;

	}
	
	

	}
	

