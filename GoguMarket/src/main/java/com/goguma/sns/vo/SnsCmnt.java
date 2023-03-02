package com.goguma.sns.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SnsCmnt {
	private String cmntNO;
	private int snsNo;
	private String cmntCn;
	private String cmntMem;
	private Date cmntYmd;
	
		
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String cmntOrder;
	private String cmntClass;
	
	
}
