package com.goguma.deal.serviceImpl;

import java.io.Console;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.common.vo.AtchVO;
import com.goguma.deal.mapper.DealMapper;
import com.goguma.deal.service.DealService;
import com.goguma.deal.vo.DealSearchVO;
import com.goguma.deal.vo.DealVO;

@Service
public class DealServiceImpl implements DealService {

	@Autowired
	private DealMapper map;

	@Override
	public List<DealVO> dealListSelect(DealSearchVO svo) {
		// 판매 리스트 전체 조회 : 페이징 O 검색X
		System.out.println(svo+"svo넘어오낫!!");
		return map.dealListSelect(svo);
	}

	@Override
	public int getcountTotal(DealSearchVO svo) {
		// 페이지수 관련
		return map.getcountTotal(svo);
	}

	@Override
	public int insertDeal(DealVO deal) {
		// 판매 상품 등록
		return map.insertDeal(deal);
	}

	@Override
	public int updateDeal(DealVO deal) {
		// 판매 상품 수정
		return map.updateDeal(deal);
	}

	@Override
	public int deleteDeal(DealVO deal) {
		// 판매 상품 삭제
		return map.deleteDeal(deal);
	}

	@Override
	public List<DealVO> getDealSeller(int dlNo) {
		// 판매자의 다른상품 리스트조회
		return map.getDealSeller(dlNo);
	}

	@Override
	public List<DealVO> getDealCtgry(int dlNo) {
		// 판매자의 상품과 유사상품(같은 카테고리)
		return map.getDealCtgry(dlNo);
	}

	@Override
	public String getId(int dlNo) {
		// TODO Auto-generated method stub
		return map.getId(dlNo);
	}

	@Override
	public DealVO getDeal(int dlNo) {
		// TODO Auto-generated method stub
		return map.getDeal(dlNo);
	}

	@Override
	public int dealHitUpdate(int dlNo) {
		// TODO Auto-generated method stub
		return map.dealHitUpdate(dlNo);
	}

	@Override
	public DealVO selectDeal(int dlNo) {
		// TODO Auto-generated method stub
		return map.selectDeal(dlNo);
	}

	@Override
	public List<AtchVO> selectDealAtch(int dlNo) {
		// TODO Auto-generated method stub
		return map.selectDealAtch(dlNo);
	}

	//채은추가
	@Override
	public List<Map> selectNtslDeal(String ntslId) {
		return map.selectNtslDeal(ntslId);
	}

}
