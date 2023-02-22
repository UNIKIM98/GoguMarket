package com.goguma.mem.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class MemVO {

	private String userId;
	private String userSe;
	private String userPw;
	private String userNm;
	private String gender;
	private String nickNm;
	private String addr;
	private String dealArea;
	private String eml;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthYmd;
	private String mblTelno;
	private String bankNm;
	private String actNo;
	private String rcmdCode;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joinYmd;
	private String userStts;
	private String socialToken;
	private String rfshToken;
	private String socialSe;
	private String atechId;
	
	
}
