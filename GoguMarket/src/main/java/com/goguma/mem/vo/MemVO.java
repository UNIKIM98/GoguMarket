package com.goguma.mem.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class MemVO {

	private String UserId;
	private String UserSe;
	private String UserPw;
	private String UserNm;
	private String Gender;
	private String NickNm;
	private String Addr;
	private String DealArea;
	private String Eml;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date BirthYmd;
	private String MblTelno;
	private String BankNm;
	private String ActNo;
	private String RcmdCode;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date JoinYmd;
	private String UserStts;
	private String SocialToken;
	private String RfshToken;
	private String SocialSe;
	private String AtechId;
	
	
}
