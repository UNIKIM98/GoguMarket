package com.goguma.common.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.common.mapper.TestMapper;
import com.goguma.common.service.TestService;
import com.goguma.common.vo.AtchVO;
import com.goguma.deal.vo.DealVO;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	TestMapper testMapper;

	@Override
	public DealVO selectDeal(int dlNo) {
		return testMapper.selectDeal(dlNo);
	}

	@Override
	public List<AtchVO> selectDealAtch(int dlNo) {
		// TODO Auto-generated method stub
		return testMapper.selectDealAtch(dlNo);
	} 
	


}
