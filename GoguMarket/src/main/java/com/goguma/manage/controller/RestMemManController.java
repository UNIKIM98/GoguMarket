package com.goguma.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goguma.auct.vo.AuctMemVO;
import com.goguma.common.service.CommonCodeService;
import com.goguma.common.vo.CommonCodeVO;
import com.goguma.common.vo.CommonPaging;
import com.goguma.deal.service.DealService;
import com.goguma.deal.vo.DealVO;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.MemVO;

@RestController
public class RestMemManController {
	@Autowired
	CommonCodeService common;

	@Autowired
	MemService member;
	
	@Autowired
	DealService Deal;

	@GetMapping("/admin/keyValue")
	public Map<String, Object> keyValue() {
		Map<String, Object> map = new HashMap<String, Object>();

		List<CommonCodeVO> selist = new ArrayList();
		List<CommonCodeVO> codelist = new ArrayList();
		List<CommonCodeVO> searchlist = new ArrayList();

		selist = common.codeList("003");
		codelist = common.codeList("004");
		searchlist = common.codeList("009");

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
		page.setPageUnit(3); // 한 페이지에 출력할 레코드 건수
		page.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		vo.setFirst(page.getFirst());
		vo.setLast(page.getLast());
		

		page.setTotalRecord(member.getcountTotal(vo));
		map.put("list",member.selectMemberList(vo));
		map.put("page",page);

		return map;
	}

	
	

	@PostMapping("/updateStts")
	public int updateStts(MemVO vo) {
		System.out.println(vo);

		int cnt = member.updateStts(vo);

		return cnt;
	}

	@PostMapping("/deleteMember")
	public int deleteMember(MemVO vo) {
		System.out.println(vo);

		int cnt = member.deleteMember(vo);

		return cnt;
	}
	
	
	@GetMapping("/admin/boradkeyValue")
	public Map<String, Object> boradkeyValue() {
		Map<String, Object> map = new HashMap<String, Object>();

		List<CommonCodeVO> categori = new ArrayList();
	
		List<CommonCodeVO> searchlist = new ArrayList();

		categori = common.codeList("001");
		searchlist = common.codeList("009");

		map.put("categori", categori);
		map.put("searchlist", searchlist);

		return map;
	}
	
	

	
	
	

}
