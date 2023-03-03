package com.goguma.rsvt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goguma.biz.service.BizMemService;
import com.goguma.common.service.CommonCodeService;
import com.goguma.rsvt.service.BizMenuService;

@Controller
public class RsvtController {
	
	/* book01~05 => 어떤건 biz에 있고 어떤건 rsvt에 있어서 다 모아놈
	 * BizController에 있음. */
	
//	@GetMapping("/book0601")
//	public String book0601() {
//		return "rsvt/book0601";
//	}
	
	@Autowired BizMemService memService; 			// 가게정보
	@Autowired private BizMenuService menuService;	//메뉴 들고오기 위함
	@Autowired CommonCodeService codeService;		//공통코드
	
	@RequestMapping("/book0601/{bizNo}")
	public String bizInfo(@PathVariable String bizNo, Model model) {
		model.addAttribute("biz", memService.bizInfo(bizNo));		//가게정보
		model.addAttribute("menu", menuService.bizMenu(bizNo));		//메뉴
		model.addAttribute("code", codeService.codeList("007"));	//공통코드(시간)
		
		return "rsvt/book0601";
	}
	
	
	
	@GetMapping("/book0602")
	public String book0602() {
		return "rsvt/book0602";
	}
	
	@GetMapping("/book0603")
	public String book0603() {
		return "rsvt/book0603";
	}
	
	@GetMapping("/book0604")
	public String book0604() {
		return "rsvt/book0604";
	}
	
	@GetMapping("/mybook01")
	public String mybook01() {
		return "myPages/mybook01";
	}
	
	@GetMapping("/mybook02")
	public String mybook02() {
		return "myPages/mybook02";
	}
	
	@GetMapping("/mybook04")
	public String mybook04() {
		return "myPages/mybook04";
	}
	
	
	
}

