package com.goguma.deal.vo;

import lombok.Data;

 
@Data
public class DealRvVoteVO {

	private String rvVtNo;	// 후기 투표 번호
	private String rvNo;	// 글번호
	private String rvvtCn;	// 투표상세내용
}
//CREATE TABLE `RV_VOTE` (
//		`RV_VT_NO`	VARCHAR2(25)	NOT NULL,
//		`RV_NO`	VARCHAR2(25)	NOT NULL,
//		`RVVT_CN`	VARCHAR2(1000)	NULL
//	);