package com.goguma.biz.service;

import java.util.List;

import com.goguma.biz.vo.BizNewsVO;

public interface BizNewsService {
	//가게 소식
	List<BizNewsVO> selectBizNews(String bizNo);
}
