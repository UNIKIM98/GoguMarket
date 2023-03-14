package com.goguma.deal.vo;


import lombok.Data;
@Data
public class DealReviewVO extends DealRvVoteVO{
	private int rvNo;	// 후기번호
	private String userId;	// 아이디
	private String pstSe;	// 게시판분류 -> 중고거래
	private String rvYmd;	// 작성일자
	private String rvCn;	// 게시글내용
	private String rvYn; 	// 숨김여부
	private int dlNo;	// 글번호
	
	
	
	/*
	 * private int rvVtNo; // 
	 * 후기 투표 번호 private String rvvtCn; // 투표상세내용
	 */}