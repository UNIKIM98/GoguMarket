package com.goguma.auct.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.auct.mapper.AuctMemMapper;
import com.goguma.auct.service.AuctMemService;
import com.goguma.auct.vo.AuctMemVO;

@Service
public class AuctMemServiceImpl implements AuctMemService{

	@Autowired
	AuctMemMapper mapper;
	
	@Override
	public int insertAuctMem(AuctMemVO vo) {
		// 입찰등록
		return mapper.insertAuctMem(vo);
	}

}
