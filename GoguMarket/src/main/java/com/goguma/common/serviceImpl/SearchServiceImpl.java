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
		// "중고거래"의 인기검색어 리스트로 조회
		return map.getPopularWord();
	}

	@Override
	public int deleteWord(SearchVO search) {
		// TODO Auto-generated method stub
		return map.deleteWord(search);
	}

//	@Override
//	public int updateWord(SearchVO search) {
//		// 매퍼에서 단건으로 업데이트한 정보들
//		// 리스트의 갯수만큼 들고와서 업데이트해줘야된다
//		return map.updateWord(search);
//	}

	@Override
	public int updateWord(List<String> list) {
		// 매퍼에서 단건으로 업데이트한 정보들
		// 리스트의 갯수만큼 들고와서 업데이트해줘야된다
		int success = 0;
		for(int i=0; i <list.size(); i++) {
			System.out.println(i);
		}
		
		return success;
	}




}
