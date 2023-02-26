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
	@GetMapping("/actInfoAjax")
	public Map actInfoAjax(HttpServletRequest request) {
		System.out.println("=======/actInfoAjax");

		// 리턴할 hashMap 생성
		HashMap<String, Object> map = new HashMap<String, Object>();

		// ※ 임시로그인
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		System.out.println("session에서 가져온 userId===" + userId);

		// 대표계좌 가져오기
		ActVO aVO = aService.getMemAct(userId);
		map.put("memAct", aVO);
		if (aVO == null) {
			map.put("memAct", "Nothing");
		}

		// 전체계좌정보
		map.put("actList", aService.getActList(userId));
		System.out.println("가져온 전체계좌 정보===========" + aService.getActList(userId));

		return map;
	}

	// ▶ 대표 계좌번호 삭제
	@GetMapping("/delMemActAjax")
	public Map delMemActAjax(HttpServletRequest request) {
		System.out.println("delMemActAjax왔음=====");
		// 리턴할 hashMap 생성
		HashMap<String, Object> map = new HashMap<String, Object>();

		// ※ 임시로그인정보 가져오기
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		System.out.println("로그인한 유저 아이디===========" + userId);

		// 메퍼에 보낼 aVO 생성
		ActVO aVO = new ActVO();
		aVO.setUserId(userId);
		System.out.println("메퍼에 보내는 aVO==========" + aVO);

		int cnt = aService.deleteMemAct(aVO);

		if (cnt == 1) {
			map.put("result", "success");
		} else {
			map.put("result", "fail");
		}
		return map;
	}

	// ▶ 대표계좌 변경
	@GetMapping("/updateMemActAjax/{actNo}")
	public Map updateMemActAjax(HttpServletRequest request, @PathVariable int actNo) {
		System.out.println("updateMemActAjax왔음=====");

		// 리턴할 hashMap 생성
		HashMap<String, Object> map = new HashMap<String, Object>();

		// ※ 임시로그인정보 가져오기
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		System.out.println("로그인한 유저 아이디===========" + userId);

		// 메퍼에 보낼 aVO 생성
		ActVO aVO = new ActVO();
		aVO.setUserId(userId);
		aVO.setActNo(actNo);
		System.out.println("메퍼에 보내는 aVO==========" + aVO);

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
	@GetMapping("/deleteActListOne/{actNo}/{memActNoYn}")
	public Map deleteActListOne(HttpServletRequest request, @PathVariable int actNo, @PathVariable String memActNoYn) {
		// 리턴할 hashMap 생성
		HashMap<String, Object> map = new HashMap<String, Object>();

		// ※ 임시로그인정보 가져오기
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		System.out.println("로그인한 유저 아이디===========" + userId);

		// 메퍼에 보낼 aVO 생성
		ActVO aVO = new ActVO();
		aVO.setUserId(userId);
		aVO.setActNo(actNo);
		System.out.println("메퍼에 보내는 aVO==========" + aVO);

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
