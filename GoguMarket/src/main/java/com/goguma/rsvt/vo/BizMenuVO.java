package com.goguma.rsvt.vo;

import java.util.List;

import lombok.Data;

@Data
public class BizMenuVO { // 가게메뉴 테이블
	private int menuNo; // 메뉴번호
	private String bizNo; // 가게id
	private String menuNm; // 메뉴이름
	private int menuPrc; // 메뉴정가(가격)
	private String menuCn; // 메뉴설명
	private String atchId; // 첨부파일id(메뉴사진)

	private List<BizMenuVO> menuList;
	private List<BizMenuVO> newList;
}
