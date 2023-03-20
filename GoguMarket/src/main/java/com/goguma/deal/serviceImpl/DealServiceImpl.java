package com.goguma.deal.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.common.vo.AtchVO;
import com.goguma.deal.mapper.DealMapper;
import com.goguma.deal.service.DealService;
import com.goguma.deal.vo.DealSearchVO;
import com.goguma.deal.vo.DealVO;
import com.goguma.mem.mapper.PointMapper;
import com.goguma.mem.vo.PointVO;

@Service
public class DealServiceImpl implements DealService {

	@Autowired
	private DealMapper map;
	@Autowired
	private PointMapper pmap;

//	@Override
//	public int insertDeal(DealVO deal) {
//		// 판매 상품 등록
//		return map.insertDeal(deal);
//	}
	
	@Override
	public int insertDeal(DealVO deal, String userId) {
		// 판매 상품 등록
		// 카테고리가 "무료나눔" 이면 200포인트 증정해라
		
		PointVO pvo = new PointVO();
		if (deal.getCtgry().equals("FR")) {
			pvo.setPoint(200); 
			pvo.setUserId(userId); // 세션의 현재 유저아이디 불러와야되는데 유유
			pvo.setPointMthd("무료나눔");
		}
		pmap.insertPoint(pvo); // 포인트 vo 에 담아주기
		
		return map.insertDeal(deal);
	}
	
	@Override
	public int updateYmd(DealVO dVO, String userId) {
		// 게시글 끌어올리기
		PointVO pvo = new PointVO();
		pvo.setPoint(-200);
		pvo.setUserId(userId); // 세션의 현재 유저아이디 불러와야되는데 유유
		pvo.setPointMthd("끌어올리기");
		pmap.insertUsedPoint(pvo);
		
		return map.updateYmd(dVO);
	}
	
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
	public List<DealSearchVO> getDealSeller(DealSearchVO vo) {
		// 판매자의 다른상품 리스트조회
		return map.getDealSeller(vo);
	}

	@Override
	public List<DealVO> getDealCtgry(int dlNo) {
		// 판매자의 상품과 유사상품(같은 카테고리)
		return map.getDealCtgry(dlNo);
	}

	@Override
	public String getId(int dlNo) {
		return map.getId(dlNo);
	}

	@Override
	public DealVO getDeal(int dlNo) {
		return map.getDeal(dlNo);
	}

	@Override
	public int dealHitUpdate(int dlNo) {
		return map.dealHitUpdate(dlNo);
	}

	@Override
	public DealVO selectDeal(int dlNo) {
		return map.selectDeal(dlNo);
	}

	@Override
	public List<AtchVO> selectDealAtch(int dlNo) {
		return map.selectDealAtch(dlNo);
	}

	//채은추가
	@Override
	public List<Map> selectNtslDeal(DealVO vo) {
		
		return map.selectNtslDeal(vo);
	}

	@Override
	public String selectPrchsDeal(int dlNo) {
		// 
		return map.selectPrchsDeal(dlNo);
	}

	@Override
	public List<Map> selectCashNtsl(String ntslId) {
		// 판매자일때의 월별 가계부 조회
		return map.selectCashNtsl(ntslId);
	}

	@Override
	public List<Map> selectCashPrchs(String prchsId) {
		// 구매자일때의 월별 가계부 조회
		return map.selectCashPrchs(prchsId);
	}

	@Override
	public List<Map> selectPrice(DealSearchVO vo) {
		//
		return map.selectPrice(vo);
	}

	@Override
	public int updatePrchsStts(DealVO dVO) {
		//
		return map.updatePrchsStts(dVO);
	}




}
