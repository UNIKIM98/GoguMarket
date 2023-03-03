package com.goguma.common.mapper;

import java.util.List;

import com.goguma.common.vo.SearchVO;

public interface SearchMapper {
	int insertSearch(SearchVO search); // 검색어등록
	List<SearchVO> getPopularWord(); // 인기검색어도출
}
