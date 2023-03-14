package com.goguma.deal.vo;

import java.util.List;

import com.goguma.common.vo.CommonCodeVO;

import lombok.Data;

 
@Data
public class DealRvVoteVO extends CommonCodeVO{

	private int rvVtNo;	// 후기 투표 번호
	private int rvNo;	// 글번호
	private String rvvtCn;	// 투표상세내용
	
	private String cn;
	private String ccn;
	
	private String commonCn; // 투표상세내용 공통코드시발
	List<String> vtList;
}
