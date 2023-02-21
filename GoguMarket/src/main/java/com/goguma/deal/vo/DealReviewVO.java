package com.goguma.deal.vo;

import lombok.Data;

@Data
public class DealReviewVO {
	private String rvNo;	// 후기번호
	private String userId;	// 아이디
	private String pstSe;	// 게시판분류
	private String rvYmd;	// 작성일자
	private String rvCn;	// 게시글내용
	private String atchId;	// 첨부파일아이디
	private String hash;	// 해시태그
	private String rvYn; 	// 숨김여부
	private String dlNo;	// 글번호
}