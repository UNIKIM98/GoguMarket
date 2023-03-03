package com.goguma.mem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goguma.mem.service.AttendService;
import com.goguma.mem.vo.AttendVO;

@Controller
public class AttendController {

	@Autowired
	AttendService aService;

	@GetMapping("/myAttend")
	public String attend(HttpServletRequest request) {
		return "myPages/myAttend";
	}

	@GetMapping("/selectAttendList")
	@ResponseBody
	public Map selectAttendList(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<String, Object> map = new HashMap<String, Object>();

		String userId = (String) session.getAttribute("userId");

		map.put("aList", aService.selectAttendList(userId));
		System.out.println(aService.selectAttendList(userId));
		return map;
	}

	// 출석이벤트
	@GetMapping("/insertAttend")
	@ResponseBody
	public AttendVO insertAttend(HttpServletRequest request, AttendVO aVO) {

		// ※ 임시 로그인 정보 가져오기
		HttpSession session = request.getSession();
		aVO.setUserId((String) session.getAttribute("userId"));

		// ＃ 랜덤 포인트 설정
		int point = (int) (Math.random() * 901) + 100;
		point = point / 100 * 100;
		aVO.setPoint(point);

		// # insert
		aService.insertAttend(aVO);

		return aVO;
	}
}
