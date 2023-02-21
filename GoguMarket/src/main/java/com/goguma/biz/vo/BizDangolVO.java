package com.goguma.biz.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BizDangolVO {

	private String dgNo;		//단골번호
	private String bizNo;		//가게ID
	private String userId;		//아이디
	private String dangolMemo;	//단골메모
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dangolYmd;		//단골된 날짜
}
