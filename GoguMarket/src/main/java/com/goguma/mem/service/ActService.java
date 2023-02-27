package com.goguma.mem.service;

import java.util.List;

import com.goguma.mem.vo.ActVO;

public interface ActService {
	// 대표계좌 관련===========================
		// ▶ 대표계좌 조회(가져오기)
		ActVO getMemAct(String userId);

		// ▶ 대표계좌 삭제
		int deleteMemAct(ActVO aVO);

		// ▶ 대표계좌 변경
		int updateMemAct(ActVO aVO);
		
		// 전체계좌 관련===========================
		// ▶ 전체 계좌 조회(최대 3개)
		List<ActVO> getActList(String userId);
		
		//▶ 전체계좌 중 하나 삭제
		int deleteActListOne(ActVO aVO);

}
