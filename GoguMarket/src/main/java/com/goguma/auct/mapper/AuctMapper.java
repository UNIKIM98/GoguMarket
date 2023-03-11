package com.goguma.auct.mapper;

import java.util.List;

import com.goguma.auct.vo.AuctVO;
import com.goguma.common.vo.AtchVO;


public interface AuctMapper {
	List<AuctVO> getAuctList(); // 경매 메인
	AuctVO getAuct(AuctVO vo); // 단건조회

	int insertAuct(AuctVO vo); // 등록
	int deleteAuct(AuctVO vo); // 삭제

	int auctHitUpdate(int auctNo); // 조회수 증가용도 근데 고장남ㅋㅋㅋ

	AuctVO selectAuctNo(int auctNo); // int auctNo를 넣어서 auctVO값 가져오기(삭제용도)
	List<AuctVO> selectUserId(String userId);// userId를 넣어서 auctVO값(여러개라서 List임)(마이페이지용도)
	List<AtchVO> selectAuctAtch(int auctNo); // Auct 게시글 한 개의 첨부파일들 다 가져오기
}
