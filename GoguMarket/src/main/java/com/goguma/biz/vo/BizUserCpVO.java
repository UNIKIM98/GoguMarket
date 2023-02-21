package com.goguma.biz.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BizUserCpVO {

	private String useCpNo;	//발급받은 쿠폰 번호
	private String CpNo;	//쿠폰번호
	private String userId;	//아이디
	private String useCpYn;	//사용유무
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date useCpYmd;//사용한날짜
}
