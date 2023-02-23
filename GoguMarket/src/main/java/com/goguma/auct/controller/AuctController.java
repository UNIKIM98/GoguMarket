package com.goguma.auct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goguma.auct.mapper.AuctMapper;
import com.goguma.auct.vo.AuctVO;

@Controller
public class AuctController {

	@Autowired
	AuctMapper auctMapper;

	@GetMapping("/auctList")

	public String getauctList(Model model) {
		model.addAttribute("lists", auctMapper.getAuctList());

		return "auction/auctList";
	}

//	@GetMapping("/auctSelect")
//	public String auctSelect(Model model) {
//		// 단일품
//		model.addAttribute("auct", auctMapper.getAuct("1"));
//		System.out.println(model.getAttribute("auct"));
//		return "auction/auctSelect111";
//	}

//	@GetMapping("/auctSelect/{id}")
//	@ResponseBody
//	public AuctVO getAuct(@PathVariable String id) {
//		//단일품목 값
//		return auctMapper.getAuct(id);
//	}

	@GetMapping("/auctInsertForm")
	public String auctInsertForm() {
		// 상품등록폼 이동
		return "auction/auctInsertForm";
	}

	@PostMapping("/auctInsert") // 등록 매핑
	public String auctInsert(AuctVO vo) {
		//▲ 리턴타입 스트링으로 바꿔주기! : 
		System.out.println(vo);
		vo.setUserId("user1");
		vo.setAtchId("");
		
		//▼if문으로 인서트 됐는지 확인하는 작업 추가!
		int n = auctMapper.insertAuct(vo);
		if (n != 0) {
			System.out.println("등록완료 나중에 이거 모달로 변경");
		} else {
			System.out.println("실패실패실패 나중에 이거 모달로 변경");
		}
		//▼ 바꾸기!
		return "auction/auctList";
	}
}
