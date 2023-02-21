 package com.goguma.deal.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class DealHistoryVO {
	private String prchsNo;	// 중고거래내역번호
	private String prchsId;	// 구매자
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul") //jackson json객체시 날짜 포맷설정
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date prchsYmd;	// 구매일자
	private String dlNo;	// 글번호
}
//CREATE TABLE `DEAL_HSTRY` (
//		`PRCHS_NO`	VARCHAR2(25)	NOT NULL,
//		`PRCHS_ID`	VARCHAR2(25)	NULL	COMMENT '회원테이블 USER_ID',
//		`PRCHS_YMD`	DATE	NULL,
//		`DL_NO`	VARCHAR2(25)	NULL
//	);