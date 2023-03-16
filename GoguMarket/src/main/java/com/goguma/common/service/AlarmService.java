package com.goguma.common.service;

import java.util.List;

import com.goguma.common.vo.AlarmVO;
import com.goguma.mem.vo.MemVO;


public interface AlarmService {

	int getcountTotal(AlarmVO vo); //페이징 총 레코드를 구하는 메소드
	
	int insertAlarm(AlarmVO vo);
	
	int checkNotifyCount(AlarmVO vo);
	
	int countNotify(String userId); //알림의 갯수를 확인
	
	List<AlarmVO> selectNotify(AlarmVO vo);
	
	boolean updateNotify(AlarmVO vo);
	
	int deleteAlm(AlarmVO vo); //알림 삭제
	

	
	
}
