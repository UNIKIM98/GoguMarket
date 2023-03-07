package com.goguma.sns.mapper;

import java.util.List;
import java.util.Map;

import com.goguma.sns.vo.SnsVO;


public interface SnsMapper {
	List<SnsVO> selectSnsList();

	int getCountTotal(SnsVO vo);

	SnsVO selectSns(int id); // sns검색

	int insertSns(SnsVO vo); // sns등록

	int deleteSns(SnsVO vo); // sns수정

	int updateSns(SnsVO vo); // sns삭제
	
	int snsHitUpdate(int snNo); // 조회수 증가
}
