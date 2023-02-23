package com.goguma.mem.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.mem.mapper.MemMapper;
import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.MemVO;

@Service
public class MemServiceImpl implements MemService {

	@Autowired
	private MemMapper mapper;
	
	@Override
	public int memberJoin(MemVO vo) {
		return mapper.memberJoin(vo);
	}

	@Override
	public boolean isIdCheck(String userId) {
		return mapper.isIdCheck(userId);
	}

	@Override
	public boolean isNickNmCheck(String nickNm) {
		return mapper.isNickNmCheck(nickNm);
	}

}
