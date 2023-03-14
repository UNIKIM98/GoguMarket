package com.goguma.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goguma.common.service.AlarmService;
import com.goguma.common.service.CommonCodeService;
import com.goguma.common.vo.AlarmVO;

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

	@PostMapping("/admin/insertAlarm")
	@ResponseBody
	public int insertAlarm(AlarmVO vo) {

		String categori = vo.getPstSe();
		System.out.println("지금 들어온 카테고리는?" + categori);

		String content = "";
		System.out.println(categori == "AD");
		System.out.println(categori.equals("AD"));

		switch (categori) {

		case "AD":
			content = "[관리자 전송 알림]";
			break;
		case "AU":
			content = "[경매낙찰 알림]";
			break;
		case "BI":
			content = "[동네가게 알림]";
			break;
		case "DE":
			content = "[중고거래 알림]";
			break;
		case "RS":
			content = "[예약관련 알림]";
			break;
		case "SN":
			content = "[동네생활 알림]";
			break;
		}
		
		String almCn = vo.getAlmCn();
		vo.setAlmCn(content+almCn);
		
		alarm.insertAlarm(vo);

		/*
		 * if (categori == "AD") { content = "[관리자 전송 알림]"; } else if (categori == "AU")
		 * { content = "[경매낙찰 알림]"; } else if (categori == "BI") { content =
		 * "[동네가게 알림]"; } else if (categori == "DE") { content = "[중고거래 알림]"; } else if
		 * (categori == "RS") { content = "[예약관련 알림]"; } else if (categori == "SN") {
		 * content = "[동네생활 알림]"; }
		 */


		int cnt = 1;

		return cnt;
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

	@RequestMapping("/admin/sendAlarm")
	@ResponseBody
	public int sendAlarm() {

		int cnt = 1;
		return cnt;
	}

}
