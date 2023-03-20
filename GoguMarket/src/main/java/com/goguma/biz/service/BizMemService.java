package com.goguma.biz.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.goguma.biz.vo.BizMemVO;
import com.goguma.biz.vo.BizSearchVO;

public interface BizMemService {
	
//	//전체 가게 조회
	List<BizMemVO> getBizList();
	
	//전체 가게 리스트 페이징, 갯수 카운팅
	List<BizMemVO> bizListPage(BizSearchVO bvo);
	int bizListCnt(BizSearchVO bvo);
		
	//가게 홈 단건조회
	BizMemVO bizInfo(String bizNo);
	
	//단골 카운팅
	List<BizMemVO> BizDangolCnt();
	
	//리뷰 카운팅
	List<BizMemVO> BizReviewCnt();
	
	//사진
	List<BizMemVO> bizImgList();
	
	//상세사진
	List<BizMemVO> bizDetailImg(String bizNo);

	// ❤ 비즈번호 가져오기
	String selectBizNo(String userId);

	//가게등록
	int bizInsert(BizMemVO vo, HttpSession session);
	
	//가게 수정하기
	BizMemVO updateBizMem(String bizNo);

	
}
