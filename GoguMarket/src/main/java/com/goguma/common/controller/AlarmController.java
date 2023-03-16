package com.goguma.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goguma.common.service.AlarmService;
import com.goguma.common.service.CommonCodeService;
import com.goguma.common.vo.AlarmVO;
import com.goguma.common.vo.CommonPaging;

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
		vo.setAlmCn(content + almCn);

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
	public Map<String, Object> selectNotify(HttpServletRequest request, String pstSe, CommonPaging page) {

		System.out.println(page + "현제 페이지");
		AlarmVO vo = new AlarmVO();
		Map<String, Object> map = new HashMap<String, Object>();

		// page.setPage(vo.getAlarmNowPage());
		page.setPageUnit(10); // 한 페이지에 출력할 레코드 건수
		page.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		vo.setFirst(page.getFirst());
		vo.setLast(page.getLast());

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		vo.setPstSe(pstSe); // vo에 가지고 온 상태 저장
		vo.setUserId(userId); // 세션에 있는 유저 아이디 불러오기

		page.setTotalRecord(alarm.getcountTotal(vo));
		List<AlarmVO> result = alarm.selectNotify(vo);

		map.put("data", result);
		map.put("page", page);

		return map;
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

	@PostMapping("/my/deleteAlm")
	@ResponseBody
	public int deleteAlm(@RequestBody List<AlarmVO> almNo) {
		System.out.println(almNo);

		int cnt=0;
		
		for (AlarmVO vo : almNo) {
			System.out.println(vo);
			int success = alarm.deleteAlm(vo);
			
			if(success == 0) {
				cnt=0;
			}else {
				cnt+=1;
			}

		}
		System.out.println("count :"+cnt);

		return cnt;
	}

}
