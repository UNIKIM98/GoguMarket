package com.goguma.deal.controller;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.goguma.deal.service.DealService;
import com.goguma.deal.vo.DealSearchVO;
import com.goguma.deal.vo.DealVO;
import com.goguma.deal.vo.Paging;

@Controller

public class DealController {

	@Autowired

	private DealService dealService;
	// private DealMapper dealMapper;

	@RequestMapping("/dealList") // 판매상품 전체 조회
	public String dealListSelect(Model model, @ModelAttribute("dsvo") DealSearchVO svo, Paging paging) {

		paging.setPageUnit(5); // 한 페이지에 출력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());

		paging.setTotalRecord(dealService.getcountTotal(svo));

		model.addAttribute("lists", dealService.dealListSelect(svo));

		return "deal/dealList"; // 뷰페이지명
	}

	// 판매상품 단건 조회 => detail에 쓸려고
	@RequestMapping("/dealdetail/{dlNo}")
	public String getDeal(@PathVariable String dlNo, Model model) {
		System.out.println("=================" + dlNo);

		int cnt = dealService.dealHitUpdate(Integer.parseInt(dlNo));
		System.out.println(cnt);

		model.addAttribute("deal", dealService.getDeal(dlNo));
		return "deal/dealdetail";
	}

	@RequestMapping("/dealform") // 딜폼창확인
	public String dealform() {
		return "deal/dealform";
	}

	@RequestMapping("/dealformsubmit") // 딜폼창확인
	public String dealform(DealVO vo, MultipartFile file) {
		System.out.println(vo + "넘어온 vo");

		String saveFolder = ("C:\\upload"); // 파일저장위치 : c 나 d 밑에다가

		if (!file.isEmpty()) {// 첨부파일이 존재하면 이름UUID해줘서 중복방지해쥼
			String fileName = UUID.randomUUID().toString();
			fileName = fileName + file.getOriginalFilename();
			File uploadFile = new File(saveFolder, fileName);

			try {
				file.transferTo(uploadFile); // 실제파일저장
			} catch (Exception e) {
				e.printStackTrace();
			}
			vo.setAtchId(file.getOriginalFilename()); // 저장할때는 원본파일명
			vo.setAtchPath(saveFolder + fileName); // 물리적 위치 디렉토리포함원본파일명
		}

		dealService.insertDeal(vo);

		return "redirect:dealList";
	}

	// 판매상품 등록 : 계좌정보와 아이디값이 없으면 등록할 수 없다 => @PostMapping("/deal/{acntno}/{id}")
	// 일단 폼데이타로 부메랑에서 들어가는지 확인해보자
	@PostMapping("/deal")
	@ResponseBody
	public DealVO insertDeal(DealVO vo, MultipartFile file) {
		return vo;

	}

	// 판매상품 수정
	@PutMapping("/deal")
	@ResponseBody
	public DealVO updateDeal(DealVO vo) {
		dealService.updateDeal(vo);
		return vo;
	}

	// 판매상품 삭제
	public Map<String, Object> deleteDeal(@PathVariable String id) {
		dealService.deleteDeal(id);
		return Collections.singletonMap("result", "success delete");
	}

	// 걍 상세정보 볼려고 띄우는거 d이거왜 dealDetail 대문자는 인식이안되냐?;
	@RequestMapping("/dealdetail") //
	public String dealdetail() {
		return "deal/dealdetail";
	}

	@RequestMapping("/dealMain") //
	public String dealMain() {
		return "deal/dealMain";
	}

}
