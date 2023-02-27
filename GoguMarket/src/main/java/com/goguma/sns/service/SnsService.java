package com.goguma.sns.service;

import java.util.List;
import java.util.Map;

import com.goguma.sns.vo.SnsVO;

public interface SnsService {
	List<SnsVO> selectSnsList();

	int getCountTotal(SnsVO vo);

	SnsVO selectSns(int id); // 사원검색

	int insertSns(SnsVO vo); // 사원등록

	int deleteSns(SnsVO vo); // 사원수정

	int updateSns(SnsVO vo); // 사원삭제

}
