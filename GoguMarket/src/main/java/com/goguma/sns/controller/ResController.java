package com.goguma.sns.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.goguma.common.service.AtchService;
import com.goguma.common.vo.AtchVO;
import com.goguma.sns.service.SnsService;
import com.goguma.sns.vo.SnsVO;

@RestController
public class ResController {
	@Autowired
	SnsService service;
	AtchService Aservice;
	
	
	@GetMapping("/selectSnsList")
	public List<SnsVO> getSnsList() {
		System.out.println("sda");

		List<SnsVO> result = service.selectSnsList();

		return result;

	}
	
	@GetMapping("/selectSns")
	@ResponseBody
	public Map<String,Object> selectSns(@RequestBody int id ,int atch) {
		System.out.println(id);
		System.out.println(atch);
		
		Map <String,Object> map = new HashMap<String,Object>();
		
		map.put("sns",service.selectSns(id));
		
		map.put("atch",service.selectAtch(atch));
	
		

		return map;

	}
	
	
	@PostMapping("/insertSns")
	public void insertSns(SnsVO vo,AtchVO avo) {
	System.out.println("안녕");
	service.insertSns(vo);
//	Aservice.insertAtch(vo);
	
	}
	
	

	}
	

