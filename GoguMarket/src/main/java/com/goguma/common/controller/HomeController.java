package com.goguma.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goguma.common.service.HomeService;

@Controller
public class HomeController {
	@Autowired
	HomeService service;

	@GetMapping("/")
	public String home() {
		return "home/home";
	}

	@GetMapping("/goguma/selectHomeListAjax/{ctgry}")
	@ResponseBody
	public Map selectHomeListAjax(@PathVariable String ctgry) {
		Map<String, List<Map>> list = new HashMap<String, List<Map>>();

		list.put("deal", service.selectHomeDeal());
		list.put("auct", service.selectHomeAuct());
		list.put("biz", service.selectHomeBiz(ctgry));
		list.put("sns", service.selectHomeSns());
		System.out.println(list.get("deal"));

		return list;
	}
}