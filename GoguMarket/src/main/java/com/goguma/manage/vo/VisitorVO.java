package com.goguma.manage.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class VisitorVO {
	
	private String LogNo;
	private String Ip;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date VstYmd;
	private String VstLoc;
}
