package com.goguma.biz.mapper;

import java.util.List;

import com.goguma.biz.vo.BizMemVO;

public interface BizMemMapper {
	
	//전체 가게 조회
	List<BizMemVO> getBizList();
		
	//가게 단건조회
	BizMemVO bizInfo(String bizNo);
	
}
