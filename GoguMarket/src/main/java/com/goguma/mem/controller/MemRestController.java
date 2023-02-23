package com.goguma.mem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.goguma.mem.service.MemService;

@RestController
public class MemRestController {
	@Autowired
	MemService mService;

	// 아이디체크
	@GetMapping("/userIdChk/{userId}")
	public String userIdChk(@PathVariable String userId) {
		System.out.println("=======================" + userId);
		boolean bool = mService.isIdCheck(userId);
		String result = "1";
		System.out.println("=============" + bool);
		if (!bool) {
			result = "0";
		}

		return result;
	}

	// 닉네임 체크
	@GetMapping("/nickNmChk/{nickNm}")
	public String nickNmChk(@PathVariable String nickNm) {
		boolean bool = mService.isNickNmCheck(nickNm);
		String result = "1";
		
		if (!bool) {
			result = "0";
		}
		
		return result;
	}
}
