package com.goguma.deal.service;

import java.util.List;

import com.goguma.deal.vo.DealVO;

public interface DealService {
	List<DealVO> dealListSelect(); // 전체 판매상품 조회
}
