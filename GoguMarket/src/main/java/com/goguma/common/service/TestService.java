package com.goguma.common.service;

import java.util.List;

import com.goguma.common.vo.AtchVO;
import com.goguma.deal.vo.DealVO;

public interface TestService {
	// Deal 게시글 정보 가져오기
	DealVO selectDealTest(int dlNo);
	
	// Deal 게시글 한 개의 첨부파일들 다 가져오기
	List<AtchVO> selectDealAtchTest(int dlNo);
	
	// Deal 게시글 update
	int updateDealTest(DealVO dVO);
	
	// Deal 게시글 삭제(DB에서 영구삭제)
	int deleteDealTest(DealVO dVO);
}
