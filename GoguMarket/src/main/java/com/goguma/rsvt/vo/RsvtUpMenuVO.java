package com.goguma.rsvt.vo;

import java.util.List;

import lombok.Data;

@Data
public class RsvtUpMenuVO extends RsvtMenuVO{
	int upMenuNo;
	int rsvtUpdateNo;
	String MenuNO;
	int amount;
	int menuPrc;
	
	List<RsvtUpMenuVO> upMenuInfo;
	
}
