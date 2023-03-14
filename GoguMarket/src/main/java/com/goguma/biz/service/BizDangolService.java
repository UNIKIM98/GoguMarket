package com.goguma.biz.service;

import java.util.List;
import java.util.Map;

import com.goguma.biz.vo.BizDangolVO;

public interface BizDangolService {

	// #단골수 카운트(단골수/biz_id담긴 BizDangolVO 리턴)
	List<BizDangolVO> countDangol();
	
	// shop별 단골수 카운팅
	int selectShopDangolCnt(String bizNo);
	
	//단골 기간 카운팅
	int selectDangolPeriod(String bizNo);
	
	//단골리스트
	List<Map> selectDangolList(String bizNo);
}
