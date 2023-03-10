package com.goguma.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.goguma.common.service.CommonCodeService;
import com.goguma.common.vo.CommonCodeVO;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.MemVO;

@RestController
public class RestMemManController {
	@Autowired
	CommonCodeService common;

	@Autowired
	MemService member;

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
	public List<MemVO> selectMemberList(MemVO vo) {
		
		System.out.println(vo.getUserSe());
		System.out.println(vo.getUserStts());
		System.out.println(vo.getSearchKey());
		
		System.out.println(member.selectMemberList(vo));
		
		return member.selectMemberList(vo);
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
	


}
