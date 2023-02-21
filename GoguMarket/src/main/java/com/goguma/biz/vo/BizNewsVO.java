package com.goguma.biz.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BizNewsVO {

	private String ntcNo;	//소식번호
	private String bizNo;	//가게번호
	private String ntcTtl;	//소식제목
	private String ntcCn;	//소식내용
	private String atchId;	//첨부파일ID
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ntcYmd;	//소식등록일자
	private int inqCnt;		//조회수
}
