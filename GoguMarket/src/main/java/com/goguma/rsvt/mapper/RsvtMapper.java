package com.goguma.rsvt.mapper;

import java.util.List;

import com.goguma.biz.vo.BizMemVO;

public interface RsvtMapper {
	//전체 가게 조회
		List<BizMemVO> getBizList();
}
