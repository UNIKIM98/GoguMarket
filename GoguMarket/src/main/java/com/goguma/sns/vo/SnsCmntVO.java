package com.goguma.sns.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SnsCmntVO {
	private int cmntNo;
	private int snsNo;
	private int groupNo;
	private String cmntCn;
	private String cmntMem;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cmntYmd;
	private String atchPath; //프로필
			
	private int recmntOrder;
	
	
	
}
