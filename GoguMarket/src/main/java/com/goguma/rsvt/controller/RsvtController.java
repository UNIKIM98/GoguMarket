package com.goguma.rsvt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goguma.rsvt.service.RsvtService;

@Controller
public class RsvtController {
	
	@Autowired
	private RsvtService rsvtService;
	
	//동네가게 예약 메인
//	@GetMapping("/book01")		
//	public String book01() {	
//		return "rsvt/book01";
//	}
	
	@RequestMapping("/book01")
	public String getBizList(Model model) {
		model.addAttribute("lists", rsvtService.getBizList());
		return "rsvt/book01";
	}
	
	
	
	@GetMapping("/book0205")	//동네가게 상세정보
	public String book0205() {
		return "rsvt/book0205";
	}
	
	@GetMapping("/book0601")
	public String book0601() {
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

