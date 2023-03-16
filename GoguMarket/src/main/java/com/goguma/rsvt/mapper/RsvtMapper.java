package com.goguma.rsvt.mapper;

import java.util.List;
import java.util.Map;

import com.goguma.rsvt.vo.RsvtMenuVO;
import com.goguma.rsvt.vo.RsvtPaymentVO;
import com.goguma.rsvt.vo.RsvtUpMenuVO;
import com.goguma.rsvt.vo.RsvtUpdateVO;
import com.goguma.rsvt.vo.RsvtVO;

public interface RsvtMapper {
	//예약테이블(rsvt) 등록
	int insertRsvtInfo(RsvtVO rsvt);
	
	//예약메뉴테이블(rsvt_menu) 등록
	int insertRsvtMenu(RsvtMenuVO rsvtMenuVo);
	
	//결제정보테이블(rsvt_payment) 등록
	int insertRsvtPayment(RsvtPaymentVO payVo);
	
	//예약간단내역
	List<Map> selectMyRsvtList(String userId);
	
	//예약상세내역
	List<Map> selectMyRsvtDetail(String userId);
	
	//예약 한건
	RsvtVO selectRsvtOne(String rsvtNo);
	
	//예약번호 가게메뉴 조회
	List<Map> selectRsvtBizMenu(String rsvtNo);
	
	//예약업데이트 테이블 insert
	int insertRsvtUpdateTbl(RsvtUpdateVO rsvtUpVo);
	
	//예약내역 삭제
	int deleteAllByRsvtNo(RsvtVO rsvtVo);
	
	//비즈페이지 예약리스트 조회
	List<RsvtVO> selectBizRsvt(String bizNo);
	
	//메뉴변경 insert
//	int insertModifyMenu(RsvtUpMenuVO upVo);
//	List<RsvtUpMenuVO> insertModifyMenu();
	
	//rsvt테이블 예약상태 업데이트
	int updateRsvtStts(String rsvtNo);
	
	//예약신청된거 업데이트
	int updateRsvtInfo(String rsvtNo);
	
	//예약신청 승인여부 업데이트
	int updateApprove(String rsvtNo);

	
}
