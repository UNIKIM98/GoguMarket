package com.goguma.auct.mapper;

import java.util.List;

import com.goguma.auct.vo.AuctVO;


public interface AuctMapper {
	List<AuctVO> getAuctList();
	AuctVO getAuct(AuctVO vo);

	int insertAuct(AuctVO vo);
//	int updateAuct(AuctVO vo);
	int deleteAuct(AuctVO vo);
}
