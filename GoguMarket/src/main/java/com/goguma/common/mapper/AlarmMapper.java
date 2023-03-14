package com.goguma.common.mapper;

import java.util.List;

import com.goguma.common.vo.AlarmVO;

public interface AlarmMapper {

	int insertAlarm(AlarmVO vo);
	
	int checkNotifyCount(AlarmVO vo);
	
	List<AlarmVO> selectNotify(AlarmVO vo);
	
	boolean updateNotify(AlarmVO vo);
	
	
}
