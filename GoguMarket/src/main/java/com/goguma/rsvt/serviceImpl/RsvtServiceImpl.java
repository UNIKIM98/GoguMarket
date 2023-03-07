package com.goguma.rsvt.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.rsvt.mapper.RsvtMapper;
import com.goguma.rsvt.service.RsvtService;
import com.goguma.rsvt.vo.RsvtMenuVO;
import com.goguma.rsvt.vo.RsvtPaymentVO;
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
	
	

}
