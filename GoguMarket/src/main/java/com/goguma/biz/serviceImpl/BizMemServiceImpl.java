package com.goguma.biz.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.biz.mapper.BizMemMapper;
import com.goguma.biz.service.BizMemService;
import com.goguma.biz.vo.BizMemVO;
import com.goguma.rsvt.vo.BizMenuVO;

@Service
public class BizMemServiceImpl implements BizMemService {

	@Autowired
	private BizMemMapper map;
	
	//가게 목록 전체조회
	@Override
	public List<BizMemVO> getBizList() {
		return map.getBizList();
	}

	//가게 상세정보 - 홈
	@Override
	public BizMemVO bizInfo(String bizNo) {
		return map.bizInfo(bizNo);
	}
}