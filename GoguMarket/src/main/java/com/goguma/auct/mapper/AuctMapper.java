package com.goguma.auct.mapper;

import java.util.List;

import com.goguma.auct.vo.AuctVO;


public interface AuctMapper {
	List<AuctVO> getAuctList();

	int insertAuct(AuctVO vo);
}
