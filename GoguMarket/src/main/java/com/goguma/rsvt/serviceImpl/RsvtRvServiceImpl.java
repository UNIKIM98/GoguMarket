package com.goguma.rsvt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.rsvt.mapper.RsvtRvMapper;
import com.goguma.rsvt.service.RsvtRvService;
import com.goguma.rsvt.vo.RsvtRvVO;

@Service
public class RsvtRvServiceImpl implements RsvtRvService{
	
	@Autowired
	private RsvtRvMapper map;
	
	//가게 후기
	@Override
	public List<RsvtRvVO> rsvtReview(String bizNo) {
		return map.rsvtReview(bizNo);
	}
	
}
