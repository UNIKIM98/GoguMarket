package com.goguma.common.service;

import java.util.List;

import com.goguma.common.vo.AlarmVO;


public interface AlarmService {

	int insertAlarm(AlarmVO vo);
	
	int checkNotifyCount(AlarmVO vo);
	
	List<AlarmVO> selectNotify(AlarmVO vo);
	
	boolean updateNotify(AlarmVO vo);
	

	
	
}
