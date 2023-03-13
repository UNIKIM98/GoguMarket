package com.goguma.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.goguma.common.service.CommonCodeService;
import com.goguma.common.vo.CommonCodeVO;

public class AlarmController {
	@Autowired
	CommonCodeService common;

	
	@GetMapping("/admin/alarmkeyValue")
	public Map<String, Object> alarmkeyValue() {
		System.out.println("gdgd");
		Map<String, Object> map = new HashMap<String, Object>();

		List<CommonCodeVO> selist = new ArrayList();
		List<CommonCodeVO> codelist = new ArrayList();
		List<CommonCodeVO> searchlist = new ArrayList();

		selist = common.codeList("003");
		codelist = common.codeList("004");
		searchlist = common.codeList("009");

		map.put("selist", selist);
		map.put("codelist", codelist);
		map.put("searchlist", searchlist);

		return map;
	}
}
