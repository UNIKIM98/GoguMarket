package com.goguma.mem.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.mem.mapper.ActMapper;
import com.goguma.mem.service.ActService;
import com.goguma.mem.vo.ActVO;

@Service
public class ActServiceImpl implements ActService {

	@Autowired
	ActMapper actMapper;

	@Override
	public List<ActVO> getActList(String userId) {
		return actMapper.getActList(userId);
	}

}
