package com.goguma.rsvt.vo;

import java.sql.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class RsvtRvVO {		//예약후기(가게이용후기)테이블
	String resRvNo;		//후기번호
	String rsvtNo;		//예약번호
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date rvYmd;			//작성일자
	String rvCn;		//게시글내용
	String atchId;		//첨부파일id
	String hash;		//해시태그
	String rvYn;		//숨김여부(신고처리)
}
