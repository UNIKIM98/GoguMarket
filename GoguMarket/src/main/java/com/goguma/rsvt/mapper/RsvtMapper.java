package com.goguma.rsvt.mapper;

import java.util.List;
import java.util.Map;

import com.goguma.rsvt.vo.RsvtMenuVO;
import com.goguma.rsvt.vo.RsvtPaymentVO;
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
}
