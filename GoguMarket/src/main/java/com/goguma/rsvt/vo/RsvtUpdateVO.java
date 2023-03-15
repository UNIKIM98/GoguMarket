package com.goguma.rsvt.vo;

import java.sql.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class RsvtUpdateVO {		//예약변경신청 테이블
	int rsvtUpdateNo;	//예약변경신청번호
	int rsvtNo2;			//예약번호
	String aprvYn;			//예약변경승인여부
	int rsvtNope;			//인원
	String rsvtWant;		//요청사항
	int rsvtPay;			//결제금액
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	//@DateTimeFormat(pattern = "yyyy-MM-dd") -> sql.Date는 이거 사용x
	Date rsvtYmd;			//예약날짜
	String rsvtTm;			//예약시간
}
