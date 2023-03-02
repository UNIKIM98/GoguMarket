package com.goguma.deal.mapper;

import java.util.List;

import com.goguma.deal.vo.DealReviewVO;

public interface DealReviewMapper {
	List<DealReviewVO> getDealRv(String dlNo); // 글번호로 -> 리뷰
}
