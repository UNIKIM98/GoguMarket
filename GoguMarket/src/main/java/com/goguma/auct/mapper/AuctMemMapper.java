package com.goguma.auct.mapper;

import java.util.List;

import com.goguma.auct.vo.AuctMemVO;

public interface AuctMemMapper {
	List<AuctMemVO> selectAuctMem(int no); // 입찰조회

	int insertAuctMem(AuctMemVO vo); // 입찰등록

	List<AuctMemVO> selectNowPrc(); // 현재 입찰가 + acutNo3
	List<AuctMemVO> bidAuction(String userId); // 입찰한 경매품
}
