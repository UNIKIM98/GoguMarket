package com.goguma.auct.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.goguma.auct.mapper.AuctMapper;
import com.goguma.auct.service.AuctService;
import com.goguma.auct.vo.AuctVO;

public class AuctServiceImpl implements AuctService{
	@Autowired
	AuctMapper auctMapper;

	@Override
	public List<AuctVO> getAuctList(AuctVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuctVO getAuct(String id) {
		// TODO Auto-generated method stub
		return auctMapper.getAuct(id);
	}

	@Override
	public int insertAuct(AuctVO auct) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAuct(AuctVO auct) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAuct(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
