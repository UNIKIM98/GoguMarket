package com.goguma.manage.vo;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class RptVO {
	
	private String RptNo;
	private String RptTrgtId;
	private String UserId;
	private String PstNo;
	private String RptSe;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String RptYmd;
	private String RptType;
	private String RptRslt;
	private int RptCnt;
	private String AtchId;
}
