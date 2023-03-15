package com.goguma.biz.mapper;

import java.util.List;
import java.util.Map;

import com.goguma.biz.vo.BizDangolVO;

public interface BizDangolMapper {

	// #단골수 카운트(BizDangolVO : count(dg_no)(==단골vo에 있는 dCount), biz_no담음!)
	List<BizDangolVO> countDangol();
	
	// shop별 단골수 카운팅
	int selectShopDangolCnt(String bizNo);
	
	//단골 기간 카운팅
	int selectDangolPeriod(String bizNo);
	
	//단골리스트
	List<Map> selectDangolList(String bizNo);
	
	//단골 한명
	List<BizDangolVO> selectDangolPersonal(String userId);

}
