package com.goguma.deal.vo;

import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class DealVO {

	private int dlNo; 	// 글번호
	private String ntslId; 	// 판매자
	private String prchsId;	// 구매자
	private String ctgry;	// 카테고리
	@JsonFormat(pattern = "yyyy-mm-dd", timezone = "Asia/Seoul") //jackson json객체시 날짜 포맷설정
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date dlYmd;		// 등록일
	private String dlTtl;	// 제목
	private String dlCn;	// 내용
	private int dlPrc;		// 가격
	private String stts;	// 거래상태
	private int inqCnt;		// 조회수
	private Date prchsYmd;	// 구매일
	private String area;	// 거래지역
	private String negoYn;	// 네고여부
	
	//  첨부파일vo 이용하여,  첨부파일을 담을 리스트.
	private List<BoardAttachVO> attachList;
	
	// 이제 이거 못함 사요나라
	private String atchId;	// 첨부파일아이디
	//첨부파일
	private String atchPath; // 첨부파일경로
}

/*CREATE TABLE `DEAL` (
1`DL_NO`	NUMBER	NULL,
2`NTSL_ID`	VARCHAR2(25)	NULL,
3`PRCHS_ID`	VARCHAR2(25)	NULL,
4`CTGRY`	VARCHAR2(50)	NULL	COMMENT '의류/생활가전/생활용품 등 제품 분류',
5`DL_YMD`	DATE	NULL,
6`DL_TTL`	VARCHAR2(25)	NULL,
7`DL_CN`	VARCHAR2(1000)	NULL,
8`DL_PRC`	NUMBER	NULL,
9`STTS`	VARCHAR2(100)	NULL	COMMENT '판매중/예약중/판매완료',
10`INQ_CNT`	NUMBER	NULL,
11`PRCHS_YMD`	DATE	NULL,
12`ATCH_ID`	VARCHAR2(25)	NULL,
13`AREA`	VARCHAR2(25)	NULL,
14`NEGO_YN`	VARCHAR2(5)	NULL
);*/
