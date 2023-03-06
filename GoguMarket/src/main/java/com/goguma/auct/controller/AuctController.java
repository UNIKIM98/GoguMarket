package com.goguma.auct.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.goguma.auct.service.AuctService;
import com.goguma.auct.vo.AuctVO;
import com.goguma.common.service.AtchService;
import com.goguma.common.vo.AtchVO;

@Controller
public class AuctController {

	@Autowired
	private AuctService auctService; // 경매 서비스영역
	@Autowired
	private AtchService atchService; // 첨부파일 서비스 영역

	@GetMapping("/auctList")
	public String getauctList(Model model) {
		model.addAttribute("lists", auctService.getAuctList());

		System.out.println(model);

		return "auction/auctList";
	}

	@GetMapping("/auctSelect/{auctNo}") // 경매 번호에 따른 단일페이지 번호 변경
	public String getAuct(@PathVariable int auctNo, Model model) {
		// 단일품목 값

		AuctVO vo = new AuctVO();
		vo.setAuctNo(auctNo);
		vo = auctService.getAuct(vo); // 단건조회 서비스 불러오기
		int cnt = auctService.auctHitUpdate(auctNo); // 조회수 증가 (근데 고장남ㅋㅋ 나중에 고침~)
		System.out.println(cnt); // 조회수 증가 확인
		System.out.println(vo);

		List<AtchVO> atchList = atchService.selectAtch(vo.getAtchId()); // 첨부파일 리스트로 조회
		System.out.println(atchList); // 첨부파일 확인

		model.addAttribute("auct", vo); // 모델에 경매관련 내용 담아줌
		model.addAttribute("atch", atchList); // 모델에 경매관련 첨부파일 담아줌.

		// 타이머 DB연결해서 하는거 일단 뒤로하기

		// 마감일 적용
		Date ddln = vo.getDdlnYmd();
		Date now = new Date();
		int result = ddln.compareTo(now);
		if (result >= 0) {
			vo.setStts(1);
			System.out.println("경매가 진행중입니다.");
		} else if (result == 0) {
			vo.setStts(1);
			System.out.println("오늘이 마지막 경매일입니다.");

		} else {
			System.out.println("이미 마감된 경매품입니다.");
			vo.setStts(2);
		}

		return "auction/auctSelect";
	}

	@GetMapping("/auctInsertForm")
	public String auctInsertForm() {
		// 상품등록폼 이동
		return "auction/auctInsertFormNew";
	}

	@PostMapping("/auctInsert") // 등록 매핑
	public String auctInsert(AuctVO vo, List<MultipartFile> files) {
		// ▲ 리턴타입 스트링으로 바꿔주기! :
		System.out.println(files + "======넘어온 파일들");

		int atchId = atchService.insertFile(files);

		vo.setUserId("user2");

		System.out.println(atchId + " : files/////////");
		System.out.println(vo);

		if (atchId > 0) {
			vo.setAtchId(atchId);
		}

		auctService.insertAuct(vo);
		return "redirect:auctList";
	}

	public Map<String, Object> auctDelete(@PathVariable String id) {
		// 상품 삭제
		auctService.deleteAuct(id);
		return Collections.singletonMap("result", "success delete");
	}

}
