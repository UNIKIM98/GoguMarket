package com.goguma.deal.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goguma.deal.mapper.DealMapper;
import com.goguma.deal.vo.DealSearchVO;
import com.goguma.deal.vo.DealVO;
import com.goguma.deal.vo.Paging;

@Controller

public class DealController {
	
	@Autowired
	//private DealService dealService;
	private DealMapper dealMapper;
	
	@RequestMapping("/dealList") // 판매상품 전체 조회
	public String dealListSelect(Model model, @ModelAttribute("dsvo") DealSearchVO svo, Paging paging) {
		
		paging.setPageUnit(1); // 한 페이지에 출력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		 
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		paging.setTotalRecord(dealMapper.getcountTotal(svo));
		
		model.addAttribute("lists", dealMapper.dealListSelect(svo));
		
		return"deal/dealList"; // 뷰페이지명
	}
	
	// 판매상품 단건 조회
	
	
	@RequestMapping("/dealform") // 딜폼창확인
	public String dealform(DealVO vo) {
		dealMapper.insertDeal(vo);
		return "deal/dealform";
	}
	
	// 판매상품 등록 : 계좌정보와 아이디값이 없으면 등록할 수 없다 => @PostMapping("/deal/{acntno}/{id}")
	// 일단 폼데이타로 부메랑에서 들어가는지 확인해보자
	@PostMapping("/deal")
	@ResponseBody
	public DealVO insertDeal(DealVO vo) {
		dealMapper.insertDeal(vo);
		return vo;
	}

	// 판매상품 수정
	@PutMapping("/deal")
	@ResponseBody
	public DealVO updateDeal(DealVO vo) {
		dealMapper.updateDeal(vo);
		return vo;
	}
	// 판매상품 삭제
	public Map<String, Object> deleteDeal(@PathVariable String id){
		dealMapper.deleteDeal(id);
		return Collections.singletonMap("result", "success delete");
	}
}
