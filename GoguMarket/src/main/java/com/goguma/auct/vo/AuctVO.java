package com.goguma.auct.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AuctVO {
	 private int auctNo;
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
