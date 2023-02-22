package com.goguma.manage.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class QstnVO {

	private String QstnNo;
	private String UserId;
	private String Ctgry;
	private String QstnTtl;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date QstnYmd;
	
	private String QstnCn;
	private String AtchId;
}
