package com.goguma.rsvt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goguma.rsvt.service.RsvtMenuService;
import com.goguma.rsvt.service.RsvtPaymentService;
import com.goguma.rsvt.service.RsvtService;
import com.goguma.rsvt.vo.RsvtPaymentVO;

@RestController
public class RsvtRestController {
	
	@Autowired RsvtService rsvtService;
	
	@GetMapping("/insertRsvtPayment")
	public List<RsvtPaymentVO> insertRsvtPayment(){
//		List<RsvtPaymentVO> result 
		return null;
		
	}
	
}
