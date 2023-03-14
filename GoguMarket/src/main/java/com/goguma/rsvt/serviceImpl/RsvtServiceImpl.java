package com.goguma.rsvt.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.rsvt.mapper.RsvtMapper;
import com.goguma.rsvt.service.RsvtService;
import com.goguma.rsvt.vo.RsvtMenuVO;
import com.goguma.rsvt.vo.RsvtPaymentVO;
import com.goguma.rsvt.vo.RsvtUpdateVO;
import com.goguma.rsvt.vo.RsvtVO;

@Service
public class RsvtServiceImpl implements RsvtService{
	
	@Autowired private RsvtMapper map;

	//예약 등록
	@Override
	public int insertRsvtInfo(RsvtVO rsvt) {
		return map.insertRsvtInfo(rsvt);
	}
	
	// 예약메뉴테이블(rsvt_menu) 등록
	@Override
	public int insertRsvtMenu(RsvtMenuVO rsvtMenuVo) {
		return map.insertRsvtMenu(rsvtMenuVo);
	}
	
	// 결제정보테이블(rsvt_payment) 등록
	@Override
	public int insertRsvtPayment(RsvtPaymentVO payVo) {
		return map.insertRsvtPayment(payVo);
	}
	
	//예약간단내역
	@Override
	public List<Map> selectMyRsvtList(String userId) {
		return map.selectMyRsvtList(userId);
	}
	
	//예약상세내역
	@Override
	public List<Map> selectMyRsvtDetail(String userId) {
		return map.selectMyRsvtDetail(userId);
	}
	
	//예약 한건
	@Override
	public RsvtVO selectRsvtOne(String rsvtNo) {
		return map.selectRsvtOne(rsvtNo);
	}

	//예약번호 가게메뉴 조회
	@Override
	public List<Map> selectRsvtBizMenu(String rsvtNo) {
		return map.selectRsvtBizMenu(rsvtNo);
	}
	
	//예약업데이트 테이블 insert
	@Override
	public int insertRsvtUpdateTbl(RsvtUpdateVO rsvtUpVo) {
		return map.insertRsvtUpdateTbl(rsvtUpVo);
	}

	//예약내역 삭제
	@Override
	public int deleteAllByRsvtNo(RsvtVO rsvtVo) {
		return map.deleteAllByRsvtNo(rsvtVo);
	}

	
	

}
