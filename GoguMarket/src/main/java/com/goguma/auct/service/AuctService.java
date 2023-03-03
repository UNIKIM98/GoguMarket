package com.goguma.auct.service;

import java.util.List;

import com.goguma.auct.vo.AuctVO;


public interface AuctService {
	List<AuctVO> getAuctList(); //경매 메인
	AuctVO getAuct(AuctVO vo); //단건조회

	int insertAuct(AuctVO vo); //등록
//	int updateAuct(AuctVO vo); //수정
	int deleteAuct(String id); //삭제
	
	int auctHitUpdate(int auctNo); //조회수 증가
}
