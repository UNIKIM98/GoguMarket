package com.goguma.sns.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.goguma.common.service.AtchService;
import com.goguma.common.vo.AtchVO;
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
	public Map<String, Object> getSns(@PathVariable int Id) {
		System.out.println(Id);

		SnsVO vo = service.selectSns(Id);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("sns", vo);
		map.put("atch", aservice.selectAtch(vo.getAtchId()));

		return map;

	}

	@PostMapping("/insertSns")
	public String insertSns(SnsVO vo, AtchVO avo, List<MultipartFile> files) {


		System.out.println(vo);
		int atchId = aservice.fileUpload(files);

		if (atchId > 0) {
			vo.setAtchId(atchId);
		}
		
		service.insertSns(vo);
//		aservice.fileUpload(null);

		return "redirect:snsMain";
	}

}
