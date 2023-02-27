package com.goguma.biz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goguma.biz.service.BizMemService;
import com.goguma.biz.service.BizNewsService;
import com.goguma.rsvt.service.BizMenuService;

@Controller
public class BizController {

	@Autowired private BizMemService memService; // 가게정보
	@Autowired private BizNewsService newsService; // 가게소식 들고오기 위함
	@Autowired private BizMenuService menuService;		//메뉴 들고오기 위함

	/* book01~05 분류는 예약으로 되어있는데 
	 * 매퍼랑 서비스 같은게 biz에 있어서 여기에다가 만듬 */
	
	// 동네가게 예약 메인(book01).
	@RequestMapping("/bookmain")
	public String getBizList(Model model) {
		model.addAttribute("lists", memService.getBizList());
		return "rsvt/book01";
	}

	// 동네가게 상세정보(book0205)
	@RequestMapping("/book0205/{bizNo}")
	public String bizInfo(@PathVariable String bizNo, Model model) {
		// 가게 정보(홈)
		model.addAttribute("biz", memService.bizInfo(bizNo));
		// 가게 소식
		model.addAttribute("news", newsService.bizNews(bizNo));
		// 가게 메뉴
		model.addAttribute("menu", menuService.bizMenu(bizNo));
		
		return "rsvt/book0205";
	}

	//==============================
	
	@GetMapping("/shop04")
	public String shop04() {
		return "biz/shop04";
	}

	@GetMapping("/shop05")
	public String shop05() {
		return "biz/shop05";
	}

	@GetMapping("/shop06")
	public String shop06() {
		return "biz/shop06";
	}

	@GetMapping("/shop0602")
	public String shop0602() {
		return "biz/shop0602";
	}

	@GetMapping("/shop07")
	public String shop07() {
		return "biz/shop07";
	}

	@GetMapping("/shop0702")
	public String shop0702() {
		return "biz/shop0702";
	}

	@GetMapping("/shop08")
	public String shop08() {
		return "biz/shop08";
	}
}
