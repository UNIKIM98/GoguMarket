package com.goguma.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goguma.common.service.AlarmService;
import com.goguma.common.service.CommonCodeService;
import com.goguma.common.vo.AlarmVO;
import com.goguma.common.vo.CommonCodeVO;

@Controller
public class AlarmController {

	@Autowired
	AlarmService alarm;

	@Autowired
	CommonCodeService code;

	

	@GetMapping("/my/myNotifyList")
	public String myNotifyList() {

		return "myPages/myNotifyList";
	}

	@GetMapping("/my/checkNotifyCount")
	@ResponseBody
	public int checkNotifyCount(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		AlarmVO vo = new AlarmVO();
		vo.setUserId(userId);
		int cnt = 99;
		cnt = alarm.checkNotifyCount(vo);
		return cnt;
	}

	@GetMapping("/my/selectNotify")
	@ResponseBody
	public List<AlarmVO> selectNotify(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		AlarmVO vo = new AlarmVO();
		vo.setUserId(userId);
		List<AlarmVO> result = alarm.selectNotify(vo);
		return result;
	}

	@RequestMapping("/my/updateNotify")
	@ResponseBody
	public Boolean updateNotify(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		AlarmVO vo = new AlarmVO();
		vo.setUserId(userId);

		boolean check = alarm.updateNotify(vo);
		return check;
	}

}
