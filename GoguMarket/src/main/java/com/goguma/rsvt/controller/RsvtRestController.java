package com.goguma.rsvt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.goguma.rsvt.service.RsvtService;
import com.goguma.rsvt.vo.RsvtPaymentVO;

@RestController
public class RsvtRestController {
	
	@Autowired RsvtService rsvtService;
	
//	@PostMapping("/insertRsvtPayment")
//	public int insertRsvtPayment(@RequestBody RsvtPaymentVO payVo){
//		int result = rsvtService.insertRsvtPayment(payVo);
//		
//		System.out.println("ajax result "+ result);
//		return result;
//		
//	}
	
}
