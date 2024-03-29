package com.goguma.sns.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.sns.mapper.SnsCmntMapper;
import com.goguma.sns.service.CmntService;
import com.goguma.sns.vo.SnsCmntVO;
import com.goguma.sns.vo.SnsVO;

@Service
public class CmntServiceImpl implements CmntService {

	@Autowired
	SnsCmntMapper map;

	@Override
	public int updateCmnt(SnsCmntVO vo) {
		// TODO Auto-generated method stub
		return map.updateCmnt(vo);
	}
	
	@Override
	public List<SnsCmntVO> SelectUserCmnt(SnsCmntVO vo) {
		// TODO Auto-generated method stub
		return map.SelectUserCmnt(vo);
	}


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
		
		System.out.println("sadasdasd"+vo);
		return map.insertReply(vo);
	}

	

	@Override
	public int deleteRreply(SnsCmntVO vo) {
		
		
		return map.deleteRreply(vo);
	}

	 
}
