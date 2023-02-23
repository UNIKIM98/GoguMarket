package com.goguma.auct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.goguma.auct.mapper.AuctMapper;
import com.goguma.auct.vo.AuctVO;

@Controller
public class AuctController {

	@Autowired AuctMapper auctMapper;
	
	@GetMapping("/auctList")

	public String getauctList(Model model) {
		model.addAttribute("lists",auctMapper.getAuctList());

		return "auction/auctList";
	}
	
	
//	@GetMapping("/auctSelect")
//	public String auctSelect(Model model) {
//		//단일품 
//		model.addAttribute("auct",auctMapper.getAuct("1"));
//		System.out.println(model.getAttribute("auct"));
//		return "auction/auctSelect111";
//	}
	
//	@GetMapping("/auctSelect/{id}")
//	@ResponseBody
//	public AuctVO getAuct(@PathVariable String id) {
//		//단일품목 값
//		return auctMapper.getAuct(id);
//	}

	
	
	
	@GetMapping("/auctInsertForm")
	public String auctInsertForm() {
		//상품등록폼
		return "auction/auctInsertForm";
	}
}
