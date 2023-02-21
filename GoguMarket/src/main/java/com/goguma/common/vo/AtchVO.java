package com.goguma.common.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AtchVO {
	private String atchId;
	private String atchNo;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date crtYmd;
	private String atchNm;
	private String orgnlNm;
	private String extn;
	private int atchSize;
	private String atchPath;	
	
}
