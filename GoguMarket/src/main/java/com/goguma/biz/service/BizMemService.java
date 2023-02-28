package com.goguma.biz.service;

import java.util.List;

import com.goguma.biz.vo.BizMemVO;
import com.goguma.rsvt.vo.BizMenuVO;

public interface BizMemService {
	
	//전체 가게 조회
	List<BizMemVO> getBizList();
		
	//가게 홈 단건조회
	BizMemVO bizInfo(String bizNo);
	
//	//가게 메뉴
//	BizMenuVO bizMenu(String bizNo);
}
