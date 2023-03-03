package com.goguma.biz.mapper;

import java.util.List;

import com.goguma.biz.vo.BizDangolVO;

public interface BizDangolMapper {

	// #단골수 카운트(BizDangolVO : count(dg_no)(==단골vo에 있는 dCount), biz_no담음!)
	List<BizDangolVO> countDangol();
}
