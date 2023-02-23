package com.goguma.deal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.deal.mapper.DealMapper;
import com.goguma.deal.service.DealService;
import com.goguma.deal.vo.DealSearchVO;
import com.goguma.deal.vo.DealVO;

@Service
public class DealServiceImpl implements DealService {

	@Autowired
	private DealMapper map;

//	@Override
//	public List<DealVO> dealListSelect() {
//		// 판매 리스트 전체 조회 : 페이징전 단순 확인용
//		return map.dealListSelect();
//	}

	@Override
	public List<DealVO> dealListSelect(DealSearchVO svo) {
		// 판매 리스트 전체 조회 : 페이징 O 검색X
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
	public int deleteDeal(String id) {
		// 판매 상품 삭제
		return map.deleteDeal(id);
	}

	@Override
	public DealVO getDeal(String dlNo) {
		// 판매 상품 단건 조회
		return map.getDeal(dlNo);
	}

	@Override
	public int dealHitUpdate(int id) {
		// TODO Auto-generated method stub
		return map.dealHitUpdate(id);
	}

}
