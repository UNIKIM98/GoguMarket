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

	@Override
	public ActVO getMemAct(String userId) {
		return actMapper.getMemAct(userId);
	}

	@Override
	public int deleteMemAct(ActVO aVO) {
		return actMapper.deleteMemAct(aVO);
	}

	@Override
	public int updateMemAct(ActVO aVO) {
		return actMapper.updateMemAct(aVO);
	}

	@Override
	public int deleteActListOne(ActVO aVO) {
		return actMapper.deleteActListOne(aVO);
	}

}
