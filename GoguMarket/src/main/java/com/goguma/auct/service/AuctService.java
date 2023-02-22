package com.goguma.auct.service;

import java.util.List;

import com.goguma.auct.vo.AuctVO;

public interface AuctService {
	List<AuctVO> getAuctList(AuctVO vo);

	AuctVO getAuct(String id); // 사원검색
	int insertAuct(AuctVO auct); // 등록
	int updateAuct(AuctVO auct); // 수정
	int deleteAuct(String id); // 삭제
}
