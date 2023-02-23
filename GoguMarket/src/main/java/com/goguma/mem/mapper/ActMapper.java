package com.goguma.mem.mapper;

import java.util.List;

import com.goguma.mem.vo.ActVO;

public interface ActMapper {
	// ▶ 전체 계좌 조회(최대 3개)
	List<ActVO> getActList(String userId);
}
