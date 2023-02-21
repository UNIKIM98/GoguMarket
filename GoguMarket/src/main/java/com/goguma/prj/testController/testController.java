package com.goguma.prj.testController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class testController {

	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@GetMapping("/layout")
	public String index() {
		//채은
		return "layout";
	}
	
	@GetMapping("/dealform")
	public String dealform() {
		return "fragments/dealform";
	}
	
	@GetMapping("/book01")
	public String book01() {
		return "book01";
	}
	
	@GetMapping("/book02")
	public String book02() {
		return "book02";
	}
	@GetMapping("/auctSelectList")
	public String auctSelectList() {
		//효근
		return "auction/auctSelectList";
	}

	@GetMapping("/auctSelect")
	public String auctSelect() {
		//효근
		return "auction/auctSelect";
	}
}
