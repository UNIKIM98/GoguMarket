package com.goguma.common.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.common.mapper.AlarmMapper;
import com.goguma.common.service.AlarmService;
import com.goguma.common.vo.AlarmVO;
import com.goguma.mem.vo.MemVO;

@Service
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	AlarmMapper alarm;

	@Override
	public int getcountTotal(AlarmVO vo) {
		// TODO Auto-generated method stub
		return alarm.getcountTotal(vo);
	}

	@Override
	public int checkNotifyCount(AlarmVO vo) {
		// TODO Auto-generated method stub
		return alarm.checkNotifyCount(vo);
	}

	@Override
	public boolean updateNotify(AlarmVO vo) {
		// TODO Auto-generated method stub
		return alarm.updateNotify(vo);
	}

	@Override
	public List<AlarmVO> selectNotify(AlarmVO vo) {
		// TODO Auto-generated method stub
		return alarm.selectNotify(vo);
	}

	@Override
	public int insertAlarm(AlarmVO vo) {

		return alarm.insertAlarm(vo);
	}

}
