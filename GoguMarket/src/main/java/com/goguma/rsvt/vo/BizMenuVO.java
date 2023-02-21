package com.goguma.rsvt.vo;

import lombok.Data;

@Data
public class BizMenuVO {	//가게메뉴 테이블
	String menuNo;		//메뉴번호
	String bizNo;		//가게id
	String menuNm;		//메뉴이름
	int menuPrc;		//메뉴정가(가격)
	String menuCn;		//메뉴설명
	String atchId;		//첨부파일id(메뉴사진)
}
