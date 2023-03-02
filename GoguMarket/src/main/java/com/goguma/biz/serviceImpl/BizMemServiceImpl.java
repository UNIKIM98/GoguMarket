package com.goguma.biz.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.biz.mapper.BizMemMapper;
import com.goguma.biz.service.BizMemService;
import com.goguma.biz.vo.BizDangolVO;
import com.goguma.biz.vo.BizMemVO;
import com.goguma.biz.vo.BizSearchVO;

@Service
public class BizMemServiceImpl implements BizMemService {

	@Autowired
	private BizMemMapper map;
	
	//가게 목록 전체조회
	@Override
	public List<BizMemVO> getBizList() {
		return map.getBizList();
	}

	//가게 상세정보(book02~05전체)
	@Override
	public BizMemVO bizInfo(String bizNo) {
		return map.bizInfo(bizNo);
	}

	//가게 목록 페이징
	@Override
	public List<BizMemVO> bizListPage(BizSearchVO bvo) {
		return map.bizListPage(bvo);
	}

	//페이지 수
	@Override
	public int bizListCnt(BizSearchVO bvo) {
		return map.bizListCnt(bvo);
	}

	//단골 카운팅
	@Override
	public int dangolCnt(String bizNo) {
		return map.dangolCnt(bizNo);
	}
	
	
}