package com.goguma.rsvt.service;

import java.util.List;

import com.goguma.biz.vo.BizMemVO;
import com.goguma.biz.vo.BizNewsVO;

public interface RsvtService {
	//전체 가게 조회
	List<BizMemVO> getBizList();
	
	//가게 홈 단건조회
	BizMemVO bizInfo(String bizNo);
	
	//가게 소식
	BizNewsVO getBizNews(String bizNo);
	
		
}
