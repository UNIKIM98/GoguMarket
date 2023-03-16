package com.goguma.deal.mapper;

import java.util.List;
import java.util.Map;

import com.goguma.deal.vo.DealReviewVO;

public interface DealReviewMapper {
	List<Map> getDealRv(DealReviewVO rvVO); // 판매자번호로 -> 리뷰
	
	int getcountTotal(DealReviewVO rvVO);
	
	int insertDealRv (DealReviewVO vo); // 후기 작성
	
	int selectRvNo (); // 셀렉트키 ㅇ
	
	List<DealReviewVO> selectGetRv(DealReviewVO vo);
	List<DealReviewVO> selectSendRv(DealReviewVO vo);
	List<DealReviewVO> selectNotSendRv(DealReviewVO vo);
	
}