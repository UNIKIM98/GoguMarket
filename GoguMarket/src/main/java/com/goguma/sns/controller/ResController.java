package com.goguma.sns.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.goguma.common.service.AtchService;
import com.goguma.sns.service.SnsService;
import com.goguma.sns.vo.SnsVO;

@RestController
public class ResController {
	@Autowired
	SnsService service;
	
	@Autowired
	AtchService aservice;

	@GetMapping("/selectSnsList")
	public List<SnsVO> getSnsList() {


		List<SnsVO> result = service.selectSnsList();

		return result;

	}

	@GetMapping("/selectSns")
	@ResponseBody
	public Map<String, Object> getSns(int id) {
		System.out.println(id);
	
		SnsVO vo = service.selectSns(id);
		 
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("sns",vo);
		map.put("atch", aservice.selectImg(vo.getAtchId()));

		return map;

	}

}
