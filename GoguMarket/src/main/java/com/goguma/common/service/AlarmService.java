package com.goguma.common.service;

import java.util.List;

import com.goguma.common.vo.AlarmVO;
import com.goguma.mem.vo.MemVO;


public interface AlarmService {

	int getcountTotal(AlarmVO vo); //페이징 총 레코드를 구하는 메소드
	
	int insertAlarm(AlarmVO vo);
	
	int checkNotifyCount(AlarmVO vo);
	
	List<AlarmVO> selectNotify(AlarmVO vo);
	
	boolean updateNotify(AlarmVO vo);
	
	int deleteAlm(List<AlarmVO>vo); //알림 삭제
	

	
	
}
