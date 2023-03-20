package com.goguma.common.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.auct.vo.AuctMemVO;
import com.goguma.common.mapper.HomeMapper;
import com.goguma.common.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService {
	@Autowired
	HomeMapper homeMapper;

	@Override
	public List<Map> selectHomeDeal() {
		return homeMapper.selectHomeDeal();
	}

	@Override
	public List<Map> selectHomeAuct() {
		return homeMapper.selectHomeAuct();
	}

	@Override
	public List<Map> selectHomeBiz() {
		return homeMapper.selectHomeBiz();
	}

	@Override
	public List<Map> selectHomeSns() {
		return homeMapper.selectHomeSns();
	}

	@Override
	public List<AuctMemVO> selectHomeAuctMem(int auctNo) {
		return homeMapper.selectHomeAuctMem(auctNo);
	}

}
