package com.goguma.common.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.goguma.common.mapper.CommonCodeMapper;
import com.goguma.common.service.CommonCodeService;

public class CommonCodeServiceImpl implements CommonCodeService{
	
	@Autowired
	CommonCodeMapper cMapper;

	@Override
	public List<CommonCodeMapper> codeList(String commonCode) {
		// 공통코드이용해서
		return cMapper.codeList(commonCode);
	}


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.common.mapper.CommonCodeMapper;
import com.goguma.common.service.CommonCodeService;
import com.goguma.common.vo.CommonCodeVO;

@Service
public class CommonCodeServiceImpl implements CommonCodeService{
	@Autowired
	CommonCodeMapper cMapper;
	
	@Override
	public List<CommonCodeVO> codeList(String commonCode) {
		// 
		return cMapper.codeList(commonCode);
	}
	
}
