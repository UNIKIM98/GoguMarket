package com.goguma.deal.vo;

import lombok.Data;

@Data
public class DealReviewVO {
	private String rvNo;	// 후기번호
	private String userId;	// 아이디
	private String pstSe;	// 게시판분류
	private String rvYmd;	// 작성일자
	private String rvCn;	// 게시글내용
	private String atchId;	// 첨부파일아이디
	private String hash;	// 해시태그
	private String rvYn; 	// 숨김여부
	private String dlNo;	// 글번호
}

//CREATE TABLE `RV` ( 총아홉개
//`RV_NO`	VARCHAR2(25)	NOT NULL,
//`USER_ID`	VARCHAR2(25)	NULL,
//`PST_SE`	VARCHAR2(25)	NULL,
//`RV_YMD`	VARCHAR(255)	NULL,
//`RV_CN`	VARCHAR2(1000)	NULL,
//`ATCH_ID`	VARCHAR2(25)	NULL,
//`HASH`	VARCHAR2(25)	NULL	COMMENT '감상평(예약일)',
//`RV_YN`	VARCHAR2(5)	NULL,
//`DL_NO`	VARCHAR2(25)	NULL
//); 