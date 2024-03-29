package com.goguma.deal.mapper;

import java.util.List;
import java.util.Map;

import com.goguma.common.vo.AtchVO;
import com.goguma.deal.vo.DealSearchVO;
import com.goguma.deal.vo.DealVO;

public interface DealMapper {

	List<DealVO> dealListSelect(DealSearchVO svo); // 페이징

	int getcountTotal(DealSearchVO svo);

	String getId(int dlNo);

	DealVO getDeal(int dlNo); // 판매상품 단건조회 (상세정보에 조회하려고 사용)

	int dealHitUpdate(int dlNo); // 조회수 증가

	List<DealSearchVO> getDealSeller(DealSearchVO vo); // 판매상품 판매자로 조회 -> 판매자의 다른상품

	List<DealVO> getDealCtgry(int dlNo); // 판매상품 판매자로 조회 -> 유사 카테고리 상품
	// 시세 조회
	List<Map> selectPrice(DealSearchVO vo);
	
	// ==========
	// 정상작동 하는 거
	int insertDeal(DealVO deal); // 판매상품 등록

	// 판매자일때의 월별 가계부조회
	List<Map> selectCashNtsl(String ntslId);
	// 구매자일때의 월별 가계부 조회
	List<Map> selectCashPrchs(String prchsId);
	
	// ===========================
	// 특정 판매자 게시글 전부 가져오기
	List<Map> selectNtslDeal(DealVO vo);
	
	String selectPrchsDeal(int dlNo);
	
	// test 에서 가져온 거
	// Deal 게시글 정보 가져오기
	DealVO selectDeal(int dlNo);

	// Deal 게시글 한 개의 첨부파일들 다 가져오기
	List<AtchVO> selectDealAtch(int dlNo);

	// Deal 게시글 update
	int updateDeal(DealVO dVO);
	
	// Deal 게시글 끌어올리깃!
	int updateYmd(DealVO dVO);

	// Deal 게시글 삭제(DB에서 영구삭제)
	int deleteDeal(DealVO dVO);
	
	int updatePrchsStts(DealVO dVO); //판매완료

}
