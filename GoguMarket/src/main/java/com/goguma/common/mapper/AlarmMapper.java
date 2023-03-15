package com.goguma.common.mapper;

import java.util.List;

import com.goguma.common.vo.AlarmVO;
import com.goguma.mem.vo.MemVO;

public interface AlarmMapper {

	int getcountTotal(AlarmVO vo); //페이징 총 레코드를 구하는 메소드
	
	int insertAlarm(AlarmVO vo); //알람을 전송하는 메소드
	
	int checkNotifyCount(AlarmVO vo); //알람이 있는지를 확인하는 메소드
	
	List<AlarmVO> selectNotify(AlarmVO vo); //전체 알람을 출력하는 메소드
	
	boolean updateNotify(AlarmVO vo); //알람의 상태를 변경하는 메소드
	
	
	
}
