package com.goguma.auct.mapper;

import java.util.List;

import com.goguma.auct.vo.AuctVO;
import com.goguma.common.vo.AtchVO;


public interface AuctMapper {
	List<AuctVO> getAuctList(); //경매 메인
	AuctVO getAuct(AuctVO vo); //단건조회

	int insertAuct(AuctVO vo); //등록
	int deleteAuct(AuctVO vo); //삭제
	
	int auctHitUpdate(int auctNo); //조회수 증가
	int ddlnAuct(); //경매 마감 확인
	
	// Auct 게시글 정보 가져오기
	AuctVO selectAuct(int auctNo);
	
	// Auct 게시글 한 개의 첨부파일들 다 가져오기
	List<AtchVO> selectAuctAtch(int auctNo);
	
}
