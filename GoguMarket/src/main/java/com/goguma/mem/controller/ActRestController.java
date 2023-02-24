package com.goguma.mem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goguma.mem.service.ActService;
import com.goguma.mem.service.MemService;

@RestController
public class ActRestController {

	@Autowired
	ActService aServie;

	@Autowired
	MemService mService;

	// ▶ 대표 계좌번호 삭제
	@GetMapping("/delActNo")
	public String delActNo(HttpServletRequest request) {
		String result = null;

		HttpSession session = request.getSession();
		int cnt = mService.deleteAct((String) session.getAttribute("userId"));

		if (cnt == 1) {
			result = "success";
		} else {
			result = "fail";
		}
		return result;
	}

}
