package com.goguma.deal.service;

import java.util.List;

import com.goguma.deal.vo.DealRvVoteVO;

public interface DealRvVoteService {
	List<DealRvVoteVO> getDealRvVote(String ntslId); // 판매자번호로 리뷰투표조회
}
