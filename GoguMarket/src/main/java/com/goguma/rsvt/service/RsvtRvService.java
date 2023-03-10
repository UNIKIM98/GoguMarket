package com.goguma.rsvt.service;

import java.util.List;

import com.goguma.biz.vo.BizSearchVO;
import com.goguma.rsvt.vo.RsvtRvVO;

public interface RsvtRvService {
	//가게 후기
	List<RsvtRvVO> rsvtReview(String bizNo);
	
	//비즈페이지 후기리스트
	List<RsvtRvVO> selectReviewList(String bizNo);
	
	//비즈페이지 후기 수 카운팅
	int selectReviewCnt(String bizNo);
}
