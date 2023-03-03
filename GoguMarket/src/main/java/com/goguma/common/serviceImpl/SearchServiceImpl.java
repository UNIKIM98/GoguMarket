package com.goguma.common.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.common.mapper.SearchMapper;
import com.goguma.common.service.SearchService;
import com.goguma.common.vo.SearchVO;

@Service
public class SearchServiceImpl implements SearchService{

	@Autowired
	private SearchMapper map;
	
	@Override
	public int insertSearch(SearchVO search) {
		// 검색어 등록 
		return map.insertSearch(search);
	}

	@Override
	public List<SearchVO> getPopularWord() {
		// 인기검색어 리스트로 조회
		return map.getPopularWord();
	}





}
