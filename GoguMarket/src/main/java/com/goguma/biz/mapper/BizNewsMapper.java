package com.goguma.biz.mapper;

import java.util.List;

import com.goguma.biz.vo.BizNewsVO;

public interface BizNewsMapper {
	//가게 소식
	List<BizNewsVO> selectBizNews(String bizNo);

}
