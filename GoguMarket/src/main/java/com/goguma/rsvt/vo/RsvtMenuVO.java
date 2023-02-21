package com.goguma.rsvt.vo;

import lombok.Data;

@Data
public class RsvtMenuVO {		//예약메뉴 테이블
	String rsvtMenuNo;		//예약메뉴 번호
	String rsvtNo;			//예약번호
	String menuNo;			//메뉴번호
	int amount;				//수량
	String userId;			//아이디
}
