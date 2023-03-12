package com.goguma.auct.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.auct.mapper.AuctMemMapper;
import com.goguma.auct.service.AuctMemService;
import com.goguma.auct.vo.AuctMemVO;
import com.goguma.auct.vo.AuctVO;

@Service
public class AuctMemServiceImpl implements AuctMemService{

	@Autowired
	AuctMemMapper auctMemMapper;
	
	@Override
	public List<AuctMemVO> selectAuctMem(int no) {

		// 입찰자 조회
		return auctMemMapper.selectAuctMem(no);
	}
	
	@Override
	public int insertAuctMem(AuctMemVO vo) {
		// 입찰등록
		return auctMemMapper.insertAuctMem(vo);
	}

	@Override
	public List<AuctMemVO> selectNowPrc() {
		// 현재 입찰가
		return auctMemMapper.selectNowPrc();
	}

	@Override
	public List<AuctMemVO> bidAuction(String userId) {
		// 입찰한 경매품
		return auctMemMapper.bidAuction(userId);
	}

}
