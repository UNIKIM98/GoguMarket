package com.goguma.common.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.common.mapper.AlarmMapper;
import com.goguma.common.service.AlarmService;
import com.goguma.common.vo.AlarmVO;
@Service
public class AlarmServiceImpl implements AlarmService{
	@Autowired
	AlarmMapper alarm;
	
	@Override
	public int insertAlarm(AlarmVO vo) {
		
		return alarm.insertAlarm(vo);
	}

}
