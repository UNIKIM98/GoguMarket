package com.goguma.auct.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AuctMemVO {
	private int auctMemNo;	// 경매참여자 번호
	private String userId;		// 참여자
	private int auctNo;		// 경매번호
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul") //jackson json객체시 날짜 포맷설정
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date auctYmd;		// 입찰일자
	private int nowPrc;			// 현재입찰가
	private int pntCnt;			// 패널티
	private String auctTtl;		// 경매품이름
}
//CREATE TABLE `AUCT_MEM` (
//		`AUCT_MEM_NO`	VARCHAR2(25)	NOT NULL,
//		`AUCT_MEM`	VARCHAR2(25)	NULL	COMMENT '회원 > USER_ID',
//		`AUCT_NO`	VARCHAR2(25)	NULL,
//		`AUCT_YMD`	DATE	NULL,
//		`NOW_PRC`	NUMBER	NULL,
//		`PNT_CNT`	NUMBER	NULL
//	);

