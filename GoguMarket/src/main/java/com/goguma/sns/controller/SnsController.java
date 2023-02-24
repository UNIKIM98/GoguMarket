package com.goguma.sns.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goguma.sns.service.SnsService;
import com.goguma.sns.vo.SnsVO;

@Controller
public class SnsController {
	@Autowired
	private SnsService service;

	@GetMapping("/snsMain")
	public String SnsMain() {
		return "sns/snsMain";
	}

	@GetMapping("/getSnsList")

	public Map<String, Object> getSnsList(List<SnsVO> list) {
		System.out.println("sda");
		Map<String, Object> map = new HashMap<String, Object>();
		List<SnsVO> result = service.getSnsList();
		
		map.put("sns", result);

		return map;

	}

	@GetMapping("/insertSns")
	public String insertSns(SnsVO vo) {

		System.out.println(vo.getUserId());
		System.out.println(vo.getSnsCn());
		System.out.println(vo.getHash());
		System.out.println(vo.getLikeNocs());
		System.out.println(vo.getCmnt());
		System.out.println(vo.getSnsYmd());
		System.out.println(vo.getArea());
		System.out.println(vo.getAtchId());
		System.out.println(vo.getInqCnt());

		service.insertSns(vo);

		return "sns/snsMain";
	}

}
