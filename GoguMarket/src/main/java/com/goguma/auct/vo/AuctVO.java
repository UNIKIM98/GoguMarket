package com.goguma.auct.vo;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goguma.common.vo.AtchVO;

import lombok.Data;

@Data
public class AuctVO extends AtchVO{
	 private int auctNo;	//경매번호
	 private String userId;	//경매유저 ID
	 
	 @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul") //jackson json객체시 날짜 포맷설정
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date regYmd;	//등록날짜

	 @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul") //jackson json객체시 날짜 포맷설정
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date ddlnYmd;	//마감날짜
	 private String auctTtl;//상품명
	 private String ctgry;	//카테고리
	 private String auctCn;	//상품설명
	 private int lowPrc;	//최저가
	 private int quickPrc;	//즉시구매가
	 private int stts;		//상태
	 private int inqCnt;	//조회수
	 private int srchCnt;	//검색수
	 private int atchId;	//첨부파일 ID
	 private String prchsId;//낙찰자 ID
	 private int prchsPrc;	//낙찰가
	 @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul") //jackson json객체시 날짜 포맷설정
	 @DateTimeFormat(pattern = "yyyy-MM-dd") //yyyy-MM-dd HH:mm:ss.SSS
	 private Date prchsYmd;	//낙찰일시
} 
