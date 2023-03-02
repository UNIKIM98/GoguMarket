package com.goguma.sns.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.sns.mapper.SnsCmntMapper;
import com.goguma.sns.service.CmntService;
import com.goguma.sns.vo.SnsCmntVO;

@Service
public class CmntServiceImpl implements CmntService {

	@Autowired
	SnsCmntMapper map;

	@Override
	public List<SnsCmntVO> SelectCmntlist(int snsNo) {
		// TODO Auto-generated method stub
		return map.SelectCmntlist(snsNo);
	}

	@Override
	public List<SnsCmntVO> SelectCmnt(SnsCmntVO vo) {
		// TODO Auto-generated method stub
		return map.SelectCmnt(vo);
	}

	@Override
	public int insertReply(SnsCmntVO vo) {
		
		return map.insertReply(vo);
	}

	@Override
	public String UpdateCmnt(SnsCmntVO vo) {
		// TODO Auto-generated method stub
		return map.UpdateCmnt(vo);
	}

	@Override
	public String deleteCmnt(int CmntNo) {
		// TODO Auto-generated method stub
		return map.deleteCmnt(CmntNo);
	}

}
