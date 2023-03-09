package com.goguma.deal.service;

import java.util.List;

import com.goguma.deal.vo.DealReviewVO;

public interface DealReviewService {

	List<DealReviewVO> getDealRv(String ntslId); // 판매자아이디로 -> 리뷰
	
	int insertDealRv (DealReviewVO vo); // 후기 작성
}
