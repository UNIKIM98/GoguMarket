package com.goguma.common.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AlarmVO {
	private String almNo;
	private String userId;
	private int alarmNowPage;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date almYmd;
	private String pstSe;
	private String almCn;
	private int cked;
	private int cnt;
	
	
	

	private int first;
	private int last; // 페이징 사용 - 마지막페이지
	
	
}
