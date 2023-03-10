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
	
}
