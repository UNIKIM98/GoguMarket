package com.goguma.biz.vo;

import lombok.Data;

@Data
public class BizMemVO extends BizDangolVO {

	private String bizNo; // 가게번호
	private String userId; // 아이디
	private String bizNm; // 상호명
	private String bizAddr; // 가게주소
	private String bizTel; // 전화번호
	private int bizSe; // 회원구분
	private String brNo; // 사업자등록번호
	private String eduFnshYn; // 교육수강현황
	private String bgngTm; // 영업시작시간
	private String endTm; // 영업종료시간
	private String atchId; // 가게사진
	private String ctgry; // 카테고리
	private String hmpgAddr; // 홈페이지주소
	private String cn; // 소개문구
	private String profAtchId; // 프로필사진

	// 후기
	private String rsvtNo; // 예약번호
	private String resRvNo; // 후기번호

	// 공통코드
	private String commonCode;
	private String commonDetailCode;
	private String commonNm;
	private String commonCn;

	// 사진 출력
	private String atchPath;
	
	// # 단골 카운트
	private int dCount;
	
	//후기 카운트
	private int rwCount;
	

}
