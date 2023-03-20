package com.goguma.mem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.goguma.mem.service.ActService;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.ActVO;
import com.goguma.mem.vo.MemVO;

import groovyjarjarantlr4.v4.parse.GrammarTreeVisitor.astOperand_return;

@RestController
public class ActRestController {

	@Autowired
	ActService aService;

	// ▶ 대표계좌 + 전체계좌 정보 들고가기
	@GetMapping("/my/actInfoAjax")
	public Map actInfoAjax(HttpServletRequest request) {		
		// 리턴할 hashMap 생성
		HashMap<String, Object> map = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		// 대표계좌 가져오기
		ActVO aVO = aService.getMemAct(userId);
		map.put("memAct", aVO);
		if (aVO == null) {
			map.put("memAct", "Nothing");
		}

		// 전체계좌정보
		map.put("actList", aService.getActList(userId));

		return map;
	}

	// ▶ 대표 계좌번호 삭제
	@GetMapping("/my/delMemActAjax")
	public Map delMemActAjax(HttpServletRequest request) {
		// 리턴할 hashMap 생성
		HashMap<String, Object> map = new HashMap<String, Object>();

		// ※ 임시로그인정보 가져오기
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		// 메퍼에 보낼 aVO 생성
		ActVO aVO = new ActVO();
		aVO.setUserId(userId);

		int cnt = aService.deleteMemAct(aVO);

		if (cnt == 1) {
			map.put("result", "success");
		} else {
			map.put("result", "fail");
		}
		return map;
	}

	// ▶ 대표계좌 변경
	@GetMapping("/my/updateMemActAjax/{actNo}")
	public Map updateMemActAjax(HttpServletRequest request, @PathVariable int actNo) {

		// 리턴할 hashMap 생성
		HashMap<String, Object> map = new HashMap<String, Object>();

		// ※ 임시로그인정보 가져오기
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		// 메퍼에 보낼 aVO 생성
		ActVO aVO = new ActVO();
		aVO.setUserId(userId);
		aVO.setActNo(actNo);

		// 대표계좌 변경
		int cnt = aService.updateMemAct(aVO);

		if (cnt == 1) {
			map.put("result", aService.getMemAct(userId));
		} else {
			map.put("result", "fail");
		}

		return map;
	}

	// ▶ 전체계좌 중 하나 삭제
	@GetMapping("/my/deleteActListOne/{actNo}/{memActNoYn}")
	public Map deleteActListOne(HttpServletRequest request, @PathVariable int actNo, @PathVariable String memActNoYn) {
		// 리턴할 hashMap 생성
		HashMap<String, Object> map = new HashMap<String, Object>();

		// ※ 임시로그인정보 가져오기
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		// 메퍼에 보낼 aVO 생성
		ActVO aVO = new ActVO();
		aVO.setUserId(userId);
		aVO.setActNo(actNo);

		// 대표계좌에서 삭제
		if (memActNoYn == "Y") {
			aService.deleteMemAct(aVO);
		}

		// 전체 계좌에서 삭제
		int cnt = aService.deleteActListOne(aVO);
		map.put("result", cnt);

		return map;

	}

}
