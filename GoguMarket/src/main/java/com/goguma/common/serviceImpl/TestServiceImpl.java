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
	public DealVO selectDealTest(int dlNo) {
		return testMapper.selectDealTest(dlNo);
	}

	@Override
	public List<AtchVO> selectDealAtchTest(int dlNo) {
		// TODO Auto-generated method stub
		return testMapper.selectDealAtchTest(dlNo);
	}

	@Override
	public int updateDealTest(DealVO dVO) {
		// TODO Auto-generated method stub
		return testMapper.updateDealTest(dVO);
	}

	@Override
	public int deleteDealTest(DealVO dVO) {
		// TODO Auto-generated method stub
		return testMapper.deleteDealTest(dVO);
	} 
	


}
