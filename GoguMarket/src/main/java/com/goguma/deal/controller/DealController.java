package com.goguma.deal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goguma.deal.service.DealService;

@Controller
public class DealController {
	
	@Autowired
	private DealService dealService;
	
	@RequestMapping("/dealList") // 판매상품 조회
	public String dealListSelect(Model model) {
		model.addAttribute("lists", dealService.dealListSelect());
		return"deal/dealList";
	}
}
