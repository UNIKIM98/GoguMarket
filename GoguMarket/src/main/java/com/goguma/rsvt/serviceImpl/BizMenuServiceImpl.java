package com.goguma.rsvt.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.rsvt.mapper.BizMenuMapper;
import com.goguma.rsvt.service.BizMenuService;
import com.goguma.rsvt.vo.BizMenuVO;

@Service
public class BizMenuServiceImpl implements BizMenuService{

	@Autowired
	private BizMenuMapper map;
	
	//가게메뉴
	@Override
	public BizMenuVO bizMenu(String bizNo) {
		return map.bizMenu(bizNo);
	}

}
