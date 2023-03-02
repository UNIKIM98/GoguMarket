package com.goguma.mem.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PointVO {
	private int pointNo;
	private String userId;
	private int point;
	private String pointMthd;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date saveYmd;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date usedYmd;
}
