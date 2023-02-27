package com.goguma.rsvt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goguma.biz.vo.BizNewsVO;
import com.goguma.rsvt.mapper.RsvtMapper;
import com.goguma.rsvt.service.RsvtService;

@Controller
public class RsvtController {
	
	@Autowired
	private RsvtService rsvtservice;
	private RsvtMapper mapper;
	
	//동네가게 예약 메인(book01)
	@RequestMapping("/bookmain")
	public String getBizList(Model model) {
		model.addAttribute("lists", rsvtservice.getBizList());
		return "rsvt/book01";
	}
	
	//동네가게 상세정보 - 홈(book0205)
	@RequestMapping("/book0205/{bizNo}")
	public String bizInfo(@PathVariable String bizNo, Model model) {
		model.addAttribute("biz",rsvtservice.bizInfo(bizNo));
		return "rsvt/book0205";
	}
	
	//동네가게 상세정보 - 탭 소식(ajax사용..)
//	@RequestMapping("/book0205/{bizNo}")
//	@ResponseBody
//	public BizNewsVO getBizNews(@PathVariable String bizNo) {
//		return mapper.getBizNews(bizNo);
//		
//	}
	
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

