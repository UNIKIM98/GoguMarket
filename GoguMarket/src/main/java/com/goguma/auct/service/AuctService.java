package com.goguma.auct.service;

import java.util.List;

import com.goguma.auct.vo.AuctVO;
import com.goguma.common.vo.AtchVO;

public interface AuctService {
	List<AuctVO> getAuctList(); // 경매 메인
	AuctVO getAuct(AuctVO vo); // 단건조회

	int insertAuct(AuctVO vo); // 등록
	int deleteAuct(AuctVO vo); // 삭제

	int auctHitUpdate(int auctNo); // 조회수 증가

	AuctVO selectAuct(int auctNo); // Auct 게시글 정보(auctNo)를 int 값으로 가져오기(삭제용도)
	List<AtchVO> selectAuctAtch(int auctNo); // Auct 게시글 한 개의 첨부파일들 다 가져오기
	
}
