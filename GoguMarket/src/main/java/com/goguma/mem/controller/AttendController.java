package com.goguma.mem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AttendController {

	@GetMapping("/attend")
	public String attend() {
		return "myPages/attend";
	}
}
