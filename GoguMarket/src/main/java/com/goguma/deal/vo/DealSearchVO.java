package com.goguma.deal.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DealSearchVO extends DealVO {
	
	Integer first;
	Integer last;
	String keyword;
	String searchTtl;
	String searchType;
	
	private String orderby;

	// 시세에 사용할 변수 : selectPrice
	Integer avg;
	Integer max;
	Integer min;
	
}
