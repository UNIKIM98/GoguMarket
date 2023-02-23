package com.goguma.auct.mapper;

import java.util.List;

import com.goguma.auct.vo.AuctVO;

public interface AuctMapper {
	List<AuctVO> getAuctList(AuctVO vo);

	AuctVO getAuct(String id); // 단건조회
	int insertAuct(AuctVO auct);// 등록
	int updateAuct(AuctVO auct);// 수정
	int deleteAuct(String id); // 삭제
}
