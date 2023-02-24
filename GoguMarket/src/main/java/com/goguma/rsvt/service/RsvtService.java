package com.goguma.rsvt.service;

import java.util.List;

import com.goguma.biz.vo.BizMemVO;

public interface RsvtService {
	//전체 가게 조회
		List<BizMemVO> getBizList();
}
