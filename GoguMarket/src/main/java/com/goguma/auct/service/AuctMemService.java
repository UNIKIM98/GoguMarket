package com.goguma.auct.service;

import java.util.List;

import com.goguma.auct.vo.AuctMemVO;

public interface AuctMemService {
	List<AuctMemVO> selectAuctMem(int no); //입찰조회
	int insertAuctMem(AuctMemVO vo); //입찰등록

}
