package com.goguma.mem.service;

import java.util.List;

import com.goguma.mem.vo.PointVO;

public interface PointService {
	// ❤ 포인트 적립(출석(100~1000p)/무료나눔()/후기작성())
	int insertPoint(PointVO pVO);

	// ❤ 포인트 사용
	int insertUsedPoint(PointVO pVO);

	// ❤ 포인트 전체조회 (관리자용)
	List<PointVO> selectPointList();

	// ❤ 특정 유저 보유 포인트 조회 (유저용)
	List<PointVO> selectPointListForUser(String userId);
}
