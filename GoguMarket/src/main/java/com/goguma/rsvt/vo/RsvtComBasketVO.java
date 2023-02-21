package com.goguma.rsvt.vo;

import lombok.Data;

@Data
public class RsvtComBasketVO {	//공유장바구니 테이블
	String comNopeNo;		//공유장바구니 참여 인원번호
	String userId;			//아이디
	String rsvtNo;			//예약번호
	String comMenuYn;		//메뉴담기 현황(완료/미완)
}
