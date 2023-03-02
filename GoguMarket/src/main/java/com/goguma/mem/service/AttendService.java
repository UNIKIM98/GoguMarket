package com.goguma.mem.service;

import java.util.List;

import com.goguma.mem.vo.AttendVO;

public interface AttendService {

	// # 출석하기 insert
	int insertAttend(AttendVO aVO);
	
	// # 전체 출석 select
	List<AttendVO> selectAttendList(String userId);
}
