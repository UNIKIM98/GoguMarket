
package com.goguma.sns.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.goguma.mem.vo.MemVO;
import com.goguma.sns.service.CmntService;
import com.goguma.sns.vo.SnsCmntVO;

@RestController
public class SnsCmntController {
	@Autowired
	CmntService service;

	@PostMapping("/goguma/insertReply")
	public Map<String, Object> insertReply(@RequestBody SnsCmntVO vo, HttpServletRequest request) {

		HttpSession session = request.getSession();

		String userId = (String) session.getAttribute("userId");

		Map<String, Object> map = new HashMap<String, Object>();
		vo.setCmntMem(userId);
		int success = service.insertReply(vo);

		map.put("result", success);

		return map;
	}

	@GetMapping("/goguma/SelectCmntlist")
	public List<SnsCmntVO> SelectCmntlist(int snsNo) {
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println(snsNo + "전체");

		List<SnsCmntVO> reply = service.SelectCmntlist(snsNo);
		

		
		

		return reply;
	}

	@PostMapping("/goguma/deleteRreply")
	public Map<String, Object> deleteRreply(@RequestBody SnsCmntVO vo) {

		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(vo + "ㅎㅇ");

		System.out.println(vo.getCmntNo() + "=========");
		int success = service.deleteRreply(vo);

		map.put("result", success);

		return map;
	}

	@PostMapping("/goguma/rreplyEdit")
	public int rreplyEdit(SnsCmntVO vo) {
		System.out.println(vo + "댓글");

		int success;
		success = service.updateCmnt(vo);

		System.out.println(success);

		return success;
	}

}
