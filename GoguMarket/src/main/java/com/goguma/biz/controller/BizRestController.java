package com.goguma.biz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goguma.biz.service.BizMemService;
import com.goguma.biz.vo.BizMemVO;

@RestController
public class BizRestController {
	
	@Autowired BizMemService memService; 	// 가게정보
	
	//사진(ajax..?)
		@GetMapping("/bizImgList")
		public List<BizMemVO> getBizImgList(){
			List<BizMemVO> result = memService.bizImgList();
			System.out.println("사진 결과============" + result);
			return result;
		}
		
}
