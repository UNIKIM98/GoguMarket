package com.goguma.auct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.goguma.auct.service.AuctMemService;
import com.goguma.auct.vo.AuctMemVO;

@RestController
public class AuctMemRestController {
	@Autowired
	AuctMemService auctMemService;
		
	@PostMapping("/insertAuctMem")
	public ModelAndView insertAuctMem (AuctMemVO vo) {
		//입찰 등록
		ModelAndView mv = new ModelAndView("redirect:AuctSelect/{auctNo}");
//		System.currentTimeMillis() 밀리초단위 시간계산
		auctMemService.insertAuctMem(vo);
		return mv;
		
	}
}