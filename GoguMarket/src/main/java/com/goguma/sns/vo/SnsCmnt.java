package com.goguma.sns.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SnsCmnt {
	private String cmntNO;
	private String snsNo;
	private String pstSe;
	private String cmntCn;
	private String cmntClass;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cmntYmd;
	private String cmntMem;
	private String cmntOrder;
	private String groupNo;
}
