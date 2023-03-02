
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
		int success =  service.insertReply(vo);
		
		System.out.println(success);
 
		map.put("result",success);
	
		
		return map;
	}
	
	
	@GetMapping("/SelectCmntlist")
	public Map<String,Object> SelectCmntlist(int snsNo) {
		Map<String,Object>map = new HashMap<String, Object>();		
		
		List<SnsCmntVO> reply = service.SelectCmntlist(snsNo);
		
		map.put("reply",reply);
		
		return map;
	}

}
