package com.goguma.deal.mapper;

import java.util.List;


import com.goguma.deal.vo.DealSearchVO;
import com.goguma.deal.vo.DealVO;

public interface DealMapper {
	
	
	//List<DealVO> dealListSelect(); // 전체 판매상품 조회
	List<DealVO> dealListSelect(DealSearchVO svo); 	 // 페이징
	int getcountTotal (DealSearchVO svo);
	
	int getId(String dlNo); 
	DealVO getDeal(String dlNo); // 판매상품 단건조회 (상세정보에 조회하려고 사용)
	List<DealVO> getDealSeller(String dlNo); // 판매상품 판매자로 조회 -> 판매자의 다른상품
	List<DealVO> getDealCtgry(String dlNo);  // 판매상품 판매자로 조회 -> 유사 카테고리 상품
	int insertDeal(DealVO deal); // 판매상품 등록
	int updateDeal(DealVO deal); // 판매상품 수정
	int deleteDeal(String dlNo);	 // 판매상품 삭제
	
	int dealHitUpdate(String dlNo); // 조회수 증가
}
