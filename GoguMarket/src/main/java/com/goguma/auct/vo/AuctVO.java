package com.goguma.auct.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AuctVO {
	 private String auctNo;
	 private String userId;
	 @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul") //jackson json객체시 날짜 포맷설정
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date regYmd;
	 private Date ddlnYmd;
	 private String auctTtl;
	 private String ctgry;
	 private String auctCn;
	 private int lowPrc;
	 private int quickPrc;
	 private int stts;
	 private int inqCnt;
	 private int srchCnt;
	 private String atchId;
} 
//CREATE TABLE `AUCT` (
//		`AUCT_NO`	VARCHAR2(25)	NULL,
//		`USER_ID`	VARCHAR2(25)	NULL,
//		`REG_YMD`	DATE	NULL,
//		`DDLN_YMD`	DATE	NULL,
//		`AUCT_TTL`	VARCHAR2(25)	NULL,
//		`CTGRY`	VARCHAR2(50)	NULL,
//		`AUCT_CN`	VARCHAR2(1000)	NULL,
//		`LOW_PRC`	NUMBER	NULL,
//		`QUICK_PRC`	NUMBER	NULL,
//		`STTS`	NUMBER	NULL	COMMENT '얘로써 경매중, 유찰, 낙찰 구분함.',
//		`INQ_CNT`	NUMBER	NULL,
//		`SRCH_CNT`	NUMBER	NULL	COMMENT '실시간 검색 순위에 사용할 COUNT',
//		`ATCH_ID`	VARCHAR2(25)	NULL
//	);
