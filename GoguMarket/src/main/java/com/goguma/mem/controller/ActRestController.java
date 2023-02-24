package com.goguma.mem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goguma.mem.service.ActService;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.ActVO;
import com.goguma.mem.vo.MemVO;

@RestController
public class ActRestController {

	@Autowired
	ActService aService;

	@Autowired
	MemService mService;

	// ▶ 대표 계좌번호 삭제
	@GetMapping("/delActNo")
	public Map delActNo(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		int cnt = mService.deleteAct((String) session.getAttribute("userId"));

		if (cnt == 1) {
			map.put("result", "success");
		} else {
			map.put("result", "fail");
		}
		return map;
	}

	@GetMapping("/actInfoAjax")
	public Map actInfoAjax() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		MemVO mVO = new MemVO();
		mVO.setUserId("user1");

		mVO = mService.selectUser(mVO);
		if (mVO.getBankNm() == null) {
			System.out.println("null");
			mVO.setBankNm("Nothing");
		}
		// ▶ 대표계좌
		map.put("memAct", mVO);

		// ▶ 계좌 전체
		map.put("actList", aService.getActList("user1"));
		return map;
	}

}
