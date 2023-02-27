package com.goguma.biz.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.biz.mapper.BizNewsMapper;
import com.goguma.biz.service.BizNewsService;
import com.goguma.biz.vo.BizNewsVO;

@Service
public class BizNewsServiceImpl implements BizNewsService{
	
	@Autowired
	private BizNewsMapper map;
	
	//가게 소식
	@Override
	public BizNewsVO bizNews(String bizNo) {
		return map.bizNews(bizNo);
	}

}
