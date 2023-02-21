package com.goguma.auct.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AuctHstryVO {
	private String auctPrchsNo;
	private String auctPrchsId;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul") //jackson json객체시 날짜 포맷설정
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date auctPrchsYmd;
	private String auctNo;
}
//CREATE TABLE `AUCT_HSTRY` (
//		`AUCT_PRCHS_NO`	VARCHAR2(25)	NOT NULL,
//		`AUCT_PRCHS_ID`	VARCHAR2(25)	NULL,
//		`AUCT_PRCHS_YMD`	DATE	NULL,
//		`AUCT_NO`	VARCHAR2(25)	NULL
//	);
