package com.goguma.rsvt.vo;

import java.sql.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class RsvtVO {	//예약내역 테이블
	String rsvtNo;			//예약번호
	String bizNo;			//가게번호
	String userId;			//예약자id(사용자id)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date rsvtYmd;			//예약날짜
	Date rsvtTm;			//예약시간
	int rsvtNope;			//인원
	String rsvtWant;		//요청사항
	int rsvtPay;			//결제금액
	Date payTm;				//결제시간
	String rsvtStts;		//예약상태
	String comOrderYn;		//함께 주문 여부
	String payMthhd;		//결제수단
	String alarmYn;			//알림여부
}
