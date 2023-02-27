package com.goguma.common.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AtchVO {
	private int atchId; //파일id(pk)
	private int atchNo; //파일세부번호
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date crtYmd; //업로드잘짜
	private String atchNm; //파일이름(uuid포함)
	private String orgnlNm; //원본파일이름
	private String extn; //확장자(jpg, png, gif ...)
	private long atchSize; //파일사이즈
	private String atchPath; //파일경로
	
}
