package com.goguma.deal.mapper;

import java.util.List;

import com.goguma.deal.vo.DealVO;

public interface DealMapper {
	List<DealVO> dealListSelect(); // 전체 판매상품 조회
}
