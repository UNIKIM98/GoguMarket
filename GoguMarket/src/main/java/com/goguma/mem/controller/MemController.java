package com.goguma.mem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemController {

	@GetMapping("/myPageTest")
	public String myPageTest() {
		return "myPages/test";
	}
}
