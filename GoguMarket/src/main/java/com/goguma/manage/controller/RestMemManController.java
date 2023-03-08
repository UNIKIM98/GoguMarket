package com.goguma.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goguma.common.service.CommonCodeService;
import com.goguma.common.vo.CommonCodeVO;
import com.goguma.mem.service.MemService;

@RestController
public class RestMemManController {
	@Autowired
	CommonCodeService common;

	@Autowired
	MemService member;

	@GetMapping("/keyValue")
	public Map<String,Object> keyValue() {
		Map<String,Object> map = new HashMap<String, Object>();
		
		List<CommonCodeVO> codelist = new ArrayList();
		
		codelist =common.codeList("004");
		
		System.out.println(codelist);
		
		
		map.put("codelist",codelist);
		
		System.out.println(map);

		

		return map;
	}

}
