package com.goguma.auct.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.goguma.auct.service.AuctMemService;
import com.goguma.auct.vo.AuctMemVO;

@Controller
public class AuctMemController {
	@Autowired
	AuctMemService auctMemService;
	
	@PostMapping("/my/insertAuctMem")
	private String insertAuctMem(AuctMemVO vo,HttpServletRequest request) {
		// 입찰 등록
		
		HttpSession session = request.getSession(); //세션값 받아옴
		String myId = (String) session.getAttribute("userId"); //세션값 중 아이디만 받아옴
		
		vo.setUserId(myId); //세션값으로 아이디 설정
		System.out.println(vo); //AuctMemVO 출력
		
		
		auctMemService.insertAuctMem(vo); //vo값 그대로 서비스의 insertAuctMem실행
		
		return "redirect:/goguma/auctSelect/"+vo.getAuctNo(); //단건조회페이지로 완료되면 단건조회페이지로~
	}
	
	
}
