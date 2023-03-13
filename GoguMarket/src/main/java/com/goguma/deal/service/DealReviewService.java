package com.goguma.deal.service;

import java.util.List;

import com.goguma.deal.vo.DealReviewVO;
import com.goguma.deal.vo.DealRvVoteVO;

public interface DealReviewService {

	List<DealReviewVO> getDealRv(String ntslId); // 판매자아이디로 -> 리뷰
	
	int insertDealRv (DealReviewVO rvVO,  List<String> vtList, String userId); // 후기 작성
	
	int selectRvNo (); // 셀렉트키 ㅇ
	
	
}
