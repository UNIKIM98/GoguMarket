package com.goguma.sns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.goguma.sns.service.SnsService;
import com.goguma.sns.vo.SnsVO;

@RestController
public class ResController {
	@Autowired
	SnsService service;
	
	@GetMapping("/selectSnsList")
	public List<SnsVO> getSnsList() {
		System.out.println("sda");

		List<SnsVO> result = service.selectSnsList();

		return result;

	}
	
	@GetMapping("/selectSns")
	@ResponseBody
	public SnsVO getSns(Model model,@RequestParam("id") int id,SnsVO vo) {
		System.out.println(id);
		 vo = service.selectSns(id);
		

		return vo;

	}
	
	

	}
	

