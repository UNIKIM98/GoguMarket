package com.goguma.auct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goguma.auct.mapper.AuctMapper;
import com.goguma.auct.service.AuctService;
import com.goguma.auct.vo.AuctVO;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;

@Controller
public class AuctController {

	@Autowired
	AuctService aServie;

	@GetMapping("/auctList")
	public String getauctList(Model model) {
		model.addAttribute("lists", aServie.getAuctList());

		return "auction/auctList";
	}


	@GetMapping("/auctSelect/{auctNo}")
	public String getAuct(@PathVariable int auctNo, Model model) {
		// 단일품목 값
		System.out.println("왔니?=============================" + auctNo);
		System.out.println(auctNo);
		AuctVO aVO = new AuctVO();
		aVO.setAuctNo(auctNo);

		model.addAttribute("auct", aServie.getAuct(aVO));
		System.out.println("==============" + aServie.getAuct(aVO));
		return "auction/auctSelect";
	}

	@GetMapping("/auctInsertForm")
	public String auctInsertForm() {
		// 상품등록폼 이동
		return "auction/auctInsertForm";
	}

	@PostMapping("/auctInsert") // 등록 매핑
	public String auctInsert(AuctVO vo) {
		// ▲ 리턴타입 스트링으로 바꿔주기! :
		System.out.println(vo);
		vo.setUserId("user1");
		vo.setAtchId("");

		// ▼if문으로 인서트 됐는지 확인하는 작업 추가!
		int n = aServie.insertAuct(vo);
		if (n != 0) {
			System.out.println("등록완료 나중에 이거 모달로 변경");
		} else {
			System.out.println("실패실패실패 나중에 이거 모달로 변경");
		}
		// ▼ 바꾸기!
		return "auction/auctList";
	}
}
