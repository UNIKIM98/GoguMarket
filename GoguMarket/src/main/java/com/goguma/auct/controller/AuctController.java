package com.goguma.auct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.goguma.auct.mapper.AuctMapper;
import com.goguma.auct.service.AuctService;
import com.goguma.auct.vo.AuctVO;
import com.goguma.common.service.AtchService;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;

@Controller
public class AuctController {

	@Autowired
	private AuctService auctService; //경매 서비스영역
	@Autowired
	private AtchService atchService; //첨부파일 서비스 영역

	@GetMapping("/auctList")
	public String getauctList(Model model) {
		model.addAttribute("lists", auctService.getAuctList());


		return "auction/auctList";
	}


	@GetMapping("/auctSelect/{auctNo}")
	public String getAuct(@PathVariable int auctNo, Model model) {
		// 단일품목 값
		System.out.println("왔니?=============================" + auctNo);
		System.out.println(auctNo);
		AuctVO aVO = new AuctVO();
		aVO.setAuctNo(auctNo);


		model.addAttribute("auct", auctService.getAuct(aVO));
		System.out.println("==============" + auctService.getAuct(aVO));

		return "auction/auctSelect";
	}

	@GetMapping("/auctInsertForm")
	public String auctInsertForm() {
		// 상품등록폼 이동
		return "auction/auctInsertForm";
	}

	@PostMapping("/auctInsert") // 등록 매핑
	public String auctInsert(AuctVO vo, List<MultipartFile> files) {
		// ▲ 리턴타입 스트링으로 바꿔주기! :
		System.out.println(vo);
		System.out.println(files + "file/////////");
		
		atchService.fileUpload(files);
		int atchId = atchService.fileUpload(files);
		
		vo.setUserId("user1");

		if(atchId > 0) {
			vo.setAtchId(atchId);
		}
		
		auctService.insertAuct(vo);
		return "auction/auctList";
	}
}
