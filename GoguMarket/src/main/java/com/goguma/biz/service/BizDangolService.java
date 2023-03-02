package com.goguma.biz.service;

import java.util.List;

import com.goguma.biz.vo.BizDangolVO;

public interface BizDangolService {

	// #단골수 카운트(단골수/biz_id담긴 BizDangolVO 리턴)
	List<BizDangolVO> countDangol();
}
