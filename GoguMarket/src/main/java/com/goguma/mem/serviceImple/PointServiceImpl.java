package com.goguma.mem.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.mem.mapper.PointMapper;
import com.goguma.mem.service.PointService;
import com.goguma.mem.vo.PointVO;

@Service
public class PointServiceImpl implements PointService {

	@Autowired
	PointMapper pMapper;

	@Override
	public int insertPoint(PointVO pVO) {
		// TODO Auto-generated method stub
		return pMapper.insertPoint(pVO);
	}

	@Override
	public int insertUsedPoint(PointVO pVO) {
		// TODO Auto-generated method stub
		return pMapper.insertUsedPoint(pVO);
	}

	@Override
	public List<PointVO> selectPointList() {
		// TODO Auto-generated method stub
		return pMapper.selectPointList();
	}

	@Override
	public List<PointVO> selectPointListForUser(String userId) {
		// TODO Auto-generated method stub
		return pMapper.selectPointListForUser(userId);
	}

}
