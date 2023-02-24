package com.goguma.rsvt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.biz.vo.BizMemVO;
import com.goguma.rsvt.mapper.RsvtMapper;
import com.goguma.rsvt.service.RsvtService;

@Service
public class RsvtServiceImpl implements RsvtService{
	
	@Autowired
	private RsvtMapper map;

	@Override
	public List<BizMemVO> getBizList() {
		return map.getBizList();
	}

}
