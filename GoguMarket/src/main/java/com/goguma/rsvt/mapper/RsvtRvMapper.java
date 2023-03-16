package com.goguma.rsvt.mapper;

import java.util.List;

import com.goguma.biz.vo.BizSearchVO;
import com.goguma.rsvt.vo.RsvtRvVO;

public interface RsvtRvMapper {
	//가게 후기
	List<RsvtRvVO> rsvtReview(String bizNo);
	
	//비즈페이지 후기리스트
	List<RsvtRvVO> selectReviewList(String bizNo);
	
	//비즈페이지 후기 수 카운팅
	int selectReviewCnt(String bizNo);
	
	//유저 후기
	List<RsvtRvVO> selectReviewUser(String userId);
}
