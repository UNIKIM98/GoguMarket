package com.goguma.deal.service;

import java.util.List;

import com.goguma.deal.vo.DealSearchVO;
import com.goguma.deal.vo.DealVO;

public interface DealService {
	//List<DealVO> dealListSelect(); // 전체 판매상품 조회
	List<DealVO> dealListSelect(DealSearchVO svo); 	 // 페이징
	int getcountTotal (DealSearchVO svo);
	
	int insertDeal(DealVO deal); // 판매상품 등록
	int updateDeal(DealVO deal); // 판매상품 수정
	int deleteDeal(String id);	 // 판매상품 삭제
}
