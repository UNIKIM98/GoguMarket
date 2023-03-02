package com.goguma.deal.service;

import java.util.List;

import com.goguma.deal.vo.DealReviewVO;

public interface DealReviewService {

	List<DealReviewVO> getDealRv(String dlNo); // 글번호로 -> 리뷰
}
