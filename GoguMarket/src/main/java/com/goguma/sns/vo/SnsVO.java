package com.goguma.sns.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SnsVO {
	private int snsNo;
	private String userId;
	private String snsCn; // sns 글 내용
	private String hash; // 해시태그
	private int likeNocs; // 좋아요수
	private int cmnt; // 댓글번호

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date snsYmd;
	private String area;
	private String atchId;
	private int inqCnt;

}
