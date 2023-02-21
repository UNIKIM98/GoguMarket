package com.goguma.deal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.deal.mapper.DealMapper;
import com.goguma.deal.service.DealService;
import com.goguma.deal.vo.DealVO;

@Service
public class DealServiceImpl implements DealService{

	@Autowired
	private DealMapper map;
	
	@Override
	public List<DealVO> dealListSelect() {
		// 판매 리스트 전체 조회
		return map.dealListSelect();
	}

}
