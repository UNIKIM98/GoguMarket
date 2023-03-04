package com.goguma.common.service;

import java.util.List;

import com.goguma.common.vo.AtchVO;
import com.goguma.deal.vo.DealVO;

public interface TestService {
	// Deal 게시글 정보 가져오기
	DealVO selectDeal(int dlNo);
	
	// Deal 게시글 한 개의 첨부파일들 가져오기
	List<AtchVO> selectDealAtch(int dlNo);
}
