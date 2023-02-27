package com.goguma.mem.vo;

import lombok.Data;

@Data
public class ActVO {

	// ▶ 계좌정보 리스트(최대 3개)
	private int actNo;
	private String userId;
	private String bankNm;
	private String bankActNo;
}
