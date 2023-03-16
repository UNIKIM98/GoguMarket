package com.goguma.common.service;

import java.util.List;

import com.goguma.common.vo.SearchVO;

public interface SearchService {
	
	int insertSearch(SearchVO search); // 검색어등록
	List<SearchVO> getPopularWord(); // 인기검색어도출
	
	int deleteWord(SearchVO search);
	
	int updateWord(SearchVO search);
}
