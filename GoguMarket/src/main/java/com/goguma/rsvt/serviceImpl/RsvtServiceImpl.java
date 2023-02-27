package com.goguma.rsvt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.biz.vo.BizMemVO;
import com.goguma.biz.vo.BizNewsVO;
import com.goguma.rsvt.mapper.RsvtMapper;
import com.goguma.rsvt.service.RsvtService;

@Service
public class RsvtServiceImpl implements RsvtService{
	
	@Autowired
	private RsvtMapper map;

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
	
	//가게 상세정보 - 소식(ajax)
	@Override
	public BizNewsVO getBizNews(String bizNo) {
		return map.getBizNews(bizNo);
	}
	

}
