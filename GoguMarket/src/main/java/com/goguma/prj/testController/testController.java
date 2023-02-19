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
}
