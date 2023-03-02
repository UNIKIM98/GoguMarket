package com.goguma.mem.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.mem.mapper.AttendMapper;
import com.goguma.mem.service.AttendService;
import com.goguma.mem.vo.AttendVO;

@Service
public class AttendServiceImpl implements AttendService {
	@Autowired
	AttendMapper aMapper;

	@Override
	public int insertAttend(AttendVO aVO) {
		return aMapper.insertAttend(aVO);
	}

	@Override
	public List<AttendVO> selectAttendList(String userId) {
		return aMapper.selectAttendList(userId);
	}

}
