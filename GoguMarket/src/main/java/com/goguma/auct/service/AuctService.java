package com.goguma.auct.service;

import java.util.List;

import com.goguma.auct.vo.AuctVO;


public interface AuctService {
	List<AuctVO> getAuctList();
	AuctVO getAuct(AuctVO vo);
	
	int insertAuct(AuctVO vo);
}
