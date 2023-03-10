package com.goguma.rsvt.vo;

import java.util.List;

import lombok.Data;

@Data
public class RsvtMenuVO {		//예약메뉴 테이블
	int rsvtMenuNo;		//예약메뉴 번호
	int rsvtNo;			//예약번호
	String menuNo;			//메뉴번호
	int amount;				//수량
	String userId;			//아이디
	String menuNm;		//메뉴이름
	int rsvtPay;			//결제금액

	List<RsvtMenuVO> menuInfo;
}
