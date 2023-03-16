package com.goguma.deal.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DealRvSearchVO extends DealReviewVO{
	
	Integer first;
	Integer last;
	
	String keyword;
	String searchTtl;
	String searchType;

	String ntslId;


}
