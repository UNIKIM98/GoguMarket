package com.goguma.deal.service;

import java.util.List;
import java.util.Map;

import com.goguma.deal.vo.DealReviewVO;

public interface DealReviewService {

	List<Map> getDealRv(DealReviewVO rvVO); // 판매자번호로 -> 리뷰
	
	int getcountTotal(DealReviewVO rvVO);
	
	int insertDealRv (DealReviewVO rvVO,  List<String> vtList, String userId); // 후기 작성
	
	int selectRvNo (); // 셀렉트키 ㅇ
	
	List<DealReviewVO> selectGetRv(DealReviewVO vo);
	List<DealReviewVO> selectSendRv(DealReviewVO vo);
	List<DealReviewVO> selectNotSendRv(DealReviewVO vo);
	
	
}
