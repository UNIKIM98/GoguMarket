package com.goguma.biz.vo;

import java.sql.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class BizCpVO {

	private String cpNo;		//쿠폰번호
	private String bizNo;		//가게ID
	private String couponTrgt;	//쿠폰대상
	private String couponNm;	//쿠폰이름
	private String couponCn;	//쿠폰내용(혜택)
	private String lmtYn;		//발급건수제한여부
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startYmd;		//쿠폰시작날짜
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endYmd;		//쿠폰종료날짜
	private int cpPrc;			//쿠폰가격
}
