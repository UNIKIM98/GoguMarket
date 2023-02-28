package com.goguma.sns.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.sns.mapper.SnsMapper;
import com.goguma.sns.service.SnsService;
import com.goguma.sns.vo.SnsVO;

@Service
public class SnsServiceImpl implements SnsService {
	@Autowired SnsMapper map;
	
	
	@Override
	public List<SnsVO> selectSnsList() {
		return map.selectSnsList();
	}

	@Override
	public int getCountTotal(SnsVO vo) {
		// TODO Auto-generated method stub
		return map.getCountTotal(vo);
	}

	@Override
	public SnsVO selectSns(int id) {
		
		return map.selectSns(id);
	}

	@Override
	public int insertSns(SnsVO vo) {
		
		System.out.println("넘어옴");
		
		return map.insertSns(vo);
	}

	@Override
	public int deleteSns(SnsVO vo) {
		// TODO Auto-generated method stub
		return map.deleteSns(vo);
	}

	@Override
	public int updateSns(SnsVO vo) {
		// TODO Auto-generated method stub
		return map.updateSns(vo);
	}

}
