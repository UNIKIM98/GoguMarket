package com.goguma.deal.vo;

import java.util.List;

import lombok.Data;

 
@Data
public class DealRvVoteVO {

	private int rvVtNo;	// 후기 투표 번호
	private int rvNo;	// 글번호
	private String rvvtCn;	// 투표상세내용
	
	private String cn;
	private String ccn;
	
	List<String> vtList;
}
