package com.goguma.sns.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.goguma.sns.mapper.SnsCmntMapper;
import com.goguma.sns.service.CmntService;
import com.goguma.sns.vo.SnsCmnt;


@Service
public class CmntServiceImpl implements CmntService {

	SnsCmntMapper map;

	@Override
	public List<SnsCmnt> SelectCmntlist(int snsNo) {
		// TODO Auto-generated method stub
		return map.SelectCmntlist(snsNo);
	}

	@Override
	public List<SnsCmnt> SelectCmnt(SnsCmnt vo) {
		// TODO Auto-generated method stub
		return map.SelectCmnt(vo);
	}

	@Override
	public String inserReply(SnsCmnt vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String UpdateCmnt(SnsCmnt vo) {
		// TODO Auto-generated method stub
		return map.UpdateCmnt(vo);
	}

	@Override
	public String deleteCmnt(int CmntNo) {
		// TODO Auto-generated method stub
		return map.deleteCmnt(CmntNo);
	}

}
