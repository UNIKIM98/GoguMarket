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
import com.goguma.mem.service.PointService;
import com.goguma.mem.vo.AttendVO;
import com.goguma.mem.vo.PointVO;

@Controller
public class AttendController {

	@Autowired
	AttendService aService;

	@Autowired
	PointService pService;

	// ❤ 출석이벤트 페이지 열기
	@GetMapping("/my/myAttend")
	public String attend(HttpServletRequest request) {
		return "myPages/myAttend";
	}

	// ❤ 특정 유저 출석률 리스트 아작스
	@GetMapping("/my/selectAttendList")
	@ResponseBody
	public Map selectAttendList(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<String, Object> map = new HashMap<String, Object>();

		String userId = (String) session.getAttribute("userId");

		map.put("aList", aService.selectAttendList(userId));
		return map;
	}

	// ❤ 출석하기(insert)
	@GetMapping("/my/insertAttend")
	@ResponseBody
	public AttendVO insertAttend(HttpServletRequest request, AttendVO aVO) {

		// ※ 임시 로그인 정보 가져오기
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		aVO.setUserId(userId);

		// ＃ 랜덤 포인트 설정
		int point = (int) (Math.random() * 901) + 100;
		point = point / 100 * 100;
		aVO.setPoint(point);

		// ▼ 출석 테이블 insert
		aService.insertAttend(aVO);
		
		PointVO pVO = new PointVO();
		
		pVO.setPoint(point);
		pVO.setPointMthd("출석하기");
		pVO.setUserId(userId);
		//→ 포인트 Pk >> selectKey로 설정
		//→ 포인트 적립일 >> mapper에서 current date로 설정
		
		// ▼ 포인트 테이블 insert
		pService.insertPoint(pVO);

		return aVO;
	}
}
