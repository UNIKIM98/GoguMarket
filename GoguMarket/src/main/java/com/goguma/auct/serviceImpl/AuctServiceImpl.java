package com.goguma.auct.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.auct.mapper.AuctMapper;
import com.goguma.auct.service.AuctService;
import com.goguma.auct.vo.AuctVO;

@Service
public class AuctServiceImpl implements AuctService{
	@Autowired
	AuctMapper auctMapper;

	@Override
	public List<AuctVO> getAuctList() {
		// 경매 리스트 전체 조회
		return auctMapper.getAuctList();
	}
	
	@Override
	public AuctVO getAuct(AuctVO vo) {
		// 단건 조회
		return auctMapper.getAuct(vo);
		
	}
	
	@Override
	public int insertAuct(AuctVO vo) {
		// 등록
		return auctMapper.insertAuct(vo);
	}
}
