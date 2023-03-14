package com.goguma.manage.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MemManController {
	

	@GetMapping("/admin/adminMember")
	public String memberManage() {

		return "admin/adminMember";
	}

	@GetMapping("/admin/adminBoard")
	public String boardManage() {

		return "admin/adminBoard";
	}
	@GetMapping("/admin/alarm/{userId}")
	public String alarm(@PathVariable String userId,Model model) {
		
		model.addAttribute("userId", userId);
		
		
		return "admin/alarm";
	}
	
	
	
	
	
}
