
package com.goguma.sns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.goguma.sns.service.CmntService;
import com.goguma.sns.vo.SnsCmnt;

@RestController
public class SnsCmntController {
	@Autowired
	CmntService service;
	
	
	@PostMapping("/insertReply")
	public ModelAndView inserReply(int snsNo,@RequestParam("cmntMem")String cmntMem,@RequestParam("cmntCn")String cmntCn,SnsCmnt vo) {
		System.out.println("gdgd");
				
		vo.setSnsNo(snsNo);
		vo.setCmntMem(cmntMem);
		vo.setCmntCn(cmntCn);
		
		System.out.println(vo);
		
		ModelAndView mv = new ModelAndView("redirect:snsMain");
		
		return mv;
	}

}
