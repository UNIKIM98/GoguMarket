package com.goguma.deal.mapper;

import java.util.List;

import com.goguma.deal.vo.DealRvVoteVO;

public interface DealRvVoteMapper {
	List<DealRvVoteVO> getDealRvVote(String ntslId); // 판매자번호로 리뷰투표조회
}
