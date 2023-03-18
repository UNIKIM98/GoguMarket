package com.goguma.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import com.goguma.common.service.AlarmService;
import com.goguma.common.service.CommonCodeService;
import com.goguma.common.vo.AlarmVO;
import com.goguma.common.vo.CommonCodeVO;
import com.goguma.common.vo.CommonPaging;

import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.MemVO;

@RestController
public class RestMemManController {
	@Autowired
	CommonCodeService common;

	@Autowired
	MemService member;
	
	@Autowired 
	AlarmService alarm;

	@GetMapping("/admin/keyValue")
	public Map<String, Object> keyValue() {
		Map<String, Object> map = new HashMap<String, Object>();

		List<CommonCodeVO> pstSe = new ArrayList();
		List<CommonCodeVO> selist = new ArrayList();
		List<CommonCodeVO> codelist = new ArrayList();
		List<CommonCodeVO> searchlist = new ArrayList();

		pstSe = common.codeList("001");
		selist = common.codeList("003");
		codelist = common.codeList("004");
		searchlist = common.codeList("009");

		map.put("pstSe",	pstSe);
		map.put("selist", selist);
		map.put("codelist", codelist);
		map.put("searchlist", searchlist);

		return map;
	}
	

	@GetMapping("/admin/selectMemberList")
	public Map<String, Object> selectMemberList(CommonPaging page, MemVO vo) {
		
		System.out.println("1번 호출" + vo.getUserNowPage());
		Map<String, Object> map = new HashMap<String, Object>();
		page.setPage(vo.getUserNowPage());
		page.setPageUnit(5); // 한 페이지에 출력할 레코드 건수
		page.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		vo.setFirst(page.getFirst());
		vo.setLast(page.getLast());
		

		page.setTotalRecord(member.getcountTotal(vo));
		map.put("list",member.selectMemberList(vo));
		map.put("page",page);

		return map;
	}

	
	@PostMapping("/admin/updateStts")
	public int updateStts(MemVO vo) {
		
	
		AlarmVO avo = new AlarmVO();
		
		String Stts = vo.getUserStts();
		String SttsCn = "";
		
		switch (Stts) {

		case "0":
			SttsCn = "[정상]";
			break;
		case "1":
			SttsCn = "[정지]";
			break;
		case "3":
			SttsCn = "[휴먼]";
			break;
		case "4":
			SttsCn = "[채금]";
			break;
	
		}
		
		
		
		avo.setPstSe("AD");
		avo.setUserId(vo.getUserId());
		avo.setAlmCn("[관리자 알림] 귀하의 아이디가 "+SttsCn+" 변경되었습니다.");
		
		int cnt = member.updateStts(vo);
		if(cnt > 0) {
			alarm.insertAlarm(avo);
			
		}

		return cnt;
	}
	
	

	@PostMapping("/deleteMember")
	public int deleteMember(MemVO vo) {
		System.out.println(vo);

		int cnt = member.deleteMember(vo);

		return cnt;
	}
	
	

	

	
	
	

}
