package com.goguma.biz.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.biz.mapper.BizDangolMapper;
import com.goguma.biz.service.BizDangolService;
import com.goguma.biz.vo.BizDangolVO;
import com.goguma.biz.vo.BizSearchVO;

@Service
public class BizDangolServiceImpl implements BizDangolService {

	@Autowired
	BizDangolMapper bMapper;

	@Override
	public List<BizDangolVO> countDangol() {
		return bMapper.countDangol();
	}
	
	//shop별 단골 카운팅
	@Override
	public int selectShopDangolCnt(String bizNo) {
		return bMapper.selectShopDangolCnt(bizNo);
	}
	
	//기간별 단골 카운팅
	@Override
	public int selectDangolPeriod(String bizNo) {
		return bMapper.selectDangolPeriod(bizNo);
	}
	
	//단골 리스트
	@Override
	public List<Map> selectDangolList(BizSearchVO bvo) {
		return bMapper.selectDangolList(bvo);
	}

	//단골 한명
	@Override
	public List<BizDangolVO> selectDangolPersonal(String userId) {
		return bMapper.selectDangolPersonal(userId);
	}
}
