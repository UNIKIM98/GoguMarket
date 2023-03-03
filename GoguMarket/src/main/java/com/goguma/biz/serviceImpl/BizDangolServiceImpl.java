package com.goguma.biz.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.biz.mapper.BizDangolMapper;
import com.goguma.biz.service.BizDangolService;
import com.goguma.biz.vo.BizDangolVO;

@Service
public class BizDangolServiceImpl implements BizDangolService {

	@Autowired
	BizDangolMapper bMapper;

	@Override
	public List<BizDangolVO> countDangol() {
		return bMapper.countDangol();
	}
}
