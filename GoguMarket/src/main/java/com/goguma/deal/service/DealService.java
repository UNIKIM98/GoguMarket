package com.goguma.deal.service;

import java.util.List;

import com.goguma.deal.vo.DealSearchVO;
import com.goguma.deal.vo.DealVO;

public interface DealService {
	//List<DealVO> dealListSelect(); // 전체 판매상품 조회
	List<DealVO> dealListSelect(DealSearchVO svo); 	 // 페이징
	int getcountTotal (DealSearchVO svo);
	
	DealVO getDeal(String dlNo); // 판매상품 단건조회 (상세정보에 조회하려고 사용)
	int insertDeal(DealVO deal); // 판매상품 등록
	int updateDeal(DealVO deal); // 판매상품 수정
	int deleteDeal(String id);	 // 판매상품 삭제
	
	int dealHitUpdate(int id); // 조회수 증가
}
