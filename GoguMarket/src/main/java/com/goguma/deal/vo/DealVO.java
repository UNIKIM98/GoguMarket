package com.goguma.deal.vo;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goguma.common.vo.AtchVO;

import lombok.Data;

@Data
public class DealVO extends AtchVO{
	
	private int dlNo; 	// 글번호
	private String ntslId; 	// 판매자
	private String prchsId;	// 구매자
	@JsonFormat(pattern = "yyyy-mm-dd", timezone = "Asia/Seoul") //jackson json객체시 날짜 포맷설정
	@DateTimeFormat(pattern = "yyyy-mm-dd") // sql쓸거면 안써도되는데 왜되는것..?지영언니꺼참고 
	private Date dlYmd;		// 등록일
	private String dlTtl;	// 제목
	private String dlCn;	// 내용
	private int dlPrc;		// 가격
	private String stts;	// 거래상태
	private int inqCnt;		// 조회수
	private Date prchsYmd;	// 구매일
	private String area;	// 거래지역
	private String negoYn;	// 네고여부
	private String ctgry;	// 카테고리
	private String atchNm;  // 파일명
	private int atchId;
	
	// 가계부 알리아스
	private String month;
	private String cnt;
	private String ncnt;
	private String totalprc;
	
	// 공통코드
	private String commonCode;
	private String commonDetailCode;
	private String commonNm;
	private String commonCn;
	
	private String ctgryNm;

}
