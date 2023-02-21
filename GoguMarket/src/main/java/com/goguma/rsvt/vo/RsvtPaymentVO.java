package com.goguma.rsvt.vo;

import java.sql.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class RsvtPaymentVO {	//결제내역 테이블
	String payNo;		//결제번호
	String rsvtNo;		//예약번호
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date payYmd;		//결제날짜(시간)
	int payAmt;			//결제금액
	String payMthd;		//결제수단
}
