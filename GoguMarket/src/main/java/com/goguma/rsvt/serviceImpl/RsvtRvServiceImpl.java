package com.goguma.rsvt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.biz.vo.BizSearchVO;
import com.goguma.rsvt.mapper.RsvtRvMapper;
import com.goguma.rsvt.service.RsvtRvService;
import com.goguma.rsvt.vo.RsvtRvVO;

@Service
public class RsvtRvServiceImpl implements RsvtRvService{
	
	@Autowired
	private RsvtRvMapper map;
	
	//가게 후기
	@Override
	public List<RsvtRvVO> rsvtReview(String bizNo) {
		return map.rsvtReview(bizNo);
	}

	//비즈페이지 후기리스트
	@Override
	public List<RsvtRvVO> selectReviewList(BizSearchVO bvo) {
		return map.selectReviewList(bvo);
	}
	
	//비즈페이지 후기리스트 카운팅
	@Override
	public int selectReviewCnt(String bizNo) {
		return map.selectReviewCnt(bizNo);
	}
	
	//유저 후기
	@Override
	public List<RsvtRvVO> selectReviewUser(String userId) {
		return map.selectReviewUser(userId);
	}
	
}
