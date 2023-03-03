package com.goguma.mem.mapper;

import java.util.List;

import com.goguma.mem.vo.AttendVO;

public interface AttendMapper {
	// # 출석하기
	int insertAttend(AttendVO aVO);

	// # 전체 출석 select
	List<AttendVO> selectAttendList(String userId);
}
