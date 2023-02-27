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
	public int isIdCheck(String userId) {
		return mapper.isIdCheck(userId);
	}

	@Override
	public int isNickNmCheck(String nickNm) {
		return mapper.isNickNmCheck(nickNm);
	}

	@Override
	public MemVO selectUser(MemVO mVo) {
		return mapper.selectUser(mVo);
	}

}
