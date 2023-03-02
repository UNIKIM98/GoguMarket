package com.goguma.biz.vo;

import lombok.Data;

@Data
public class BizSearchVO extends BizMemVO {
	
	Integer first;
	Integer last;
	String keyword;
	String searchType;
}
