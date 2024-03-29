package com.goguma.mem.serviceImple;

import java.util.List;

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
	public int getcountTotal(MemVO vo) {
		// TODO Auto-generated method stub
		return mapper.getcountTotal(vo);
	}

	@Override
	public int memberJoin(MemVO vo) {
		return mapper.memberJoin(vo);
	}

	@Override
	public List<MemVO> selectMemberList(MemVO vo) {
		// TODO Auto-generated method stub
		return mapper.selectMemberList(vo);
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
	public int updateStts(MemVO vo) {
		// TODO Auto-generated method stub
		return mapper.updateStts(vo);
	}

	@Override
	public int deleteMember(MemVO vo) {
		// TODO Auto-generated method stub
		return mapper.deleteMember(vo);
	}

	@Override
	public MemVO selectUser(MemVO mVo) {
		return mapper.selectUser(mVo);
	}

	@Override
	public int updateDealArea(MemVO mVO) {
		return mapper.updateDealArea(mVO);
	}

	@Override
	public int updateUser(MemVO mVO) {
		// TODO Auto-generated method stub
		return mapper.updateUser(mVO);
	}

	@Override
	public int isEmlCheck(String eml) {
		// TODO Auto-generated method stub
		return mapper.isEmlCheck(eml);
	}

	@Override
	public int updateUserPw(MemVO mVO) {
		// TODO Auto-generated method stub
		return mapper.updateUserPw(mVO);
	}

	@Override
	public int updateUserSe(MemVO mVO) {
		// TODO Auto-generated method stub
		return mapper.updateUserSe(mVO);
	}


}
