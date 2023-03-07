
package com.goguma.sns.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.goguma.sns.service.CmntService;
import com.goguma.sns.vo.SnsCmntVO;

@RestController
public class SnsCmntController {
	@Autowired
	CmntService service;
	
	
	@PostMapping("/insertReply")
	public Map<String,Object> insertReply(@RequestBody SnsCmntVO vo) {
				
		Map<String,Object>map = new HashMap<String, Object>();
		System.out.println(vo+"댓글 입력");
		System.out.println(vo.getSnsNo());
		System.out.println(vo.getCmntNo()+"?");
		int success =  service.insertReply(vo);
		
		map.put("result",success);
	
		
		return map;
	}
	
	
	@GetMapping("/SelectCmntlist")
	public List<SnsCmntVO> SelectCmntlist(int snsNo) {
		Map<String,Object>map = new HashMap<String, Object>();
		
		System.out.println(snsNo+"전체");
		
		List<SnsCmntVO> reply = service.SelectCmntlist(snsNo);
		

		return reply;
	}
	
	@PostMapping("/deleteRreply")
	public Map<String,Object> deleteRreply(@RequestBody SnsCmntVO vo) {
				
		
		Map<String,Object>map = new HashMap<String, Object>();
		System.out.println(vo+"ㅎㅇ");
		
		
		System.out.println(vo.getCmntNo()+"=========");
		int success =  service.deleteRreply(vo.getCmntNo());
		
		map.put("result",success);
	
		
		return map;
	}
	
	
	

}
