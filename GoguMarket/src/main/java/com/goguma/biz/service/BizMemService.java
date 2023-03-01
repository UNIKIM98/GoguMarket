package com.goguma.biz.service;

import java.util.List;

import com.goguma.biz.vo.BizMemVO;
import com.goguma.biz.vo.BizSearchVO;

public interface BizMemService {
	
	//전체 가게 조회
	List<BizMemVO> getBizList();
	
	//전체 가게 리스트 페이징, 갯수 카운팅
	List<BizMemVO> bizListPage(BizSearchVO bvo);
	int bizListCnt(BizSearchVO bvo);
		
	//가게 홈 단건조회
	BizMemVO bizInfo(String bizNo);
	
}
