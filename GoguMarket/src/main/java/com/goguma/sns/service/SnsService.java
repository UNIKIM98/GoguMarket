package com.goguma.sns.service;

import java.util.List;

import com.goguma.common.vo.AtchVO;
import com.goguma.sns.vo.SnsVO;

public interface SnsService {
	List<SnsVO> selectSnsList();

	int getCountTotal(SnsVO vo);

	SnsVO selectSns(int id); // sns조회
	
	SnsVO selectPerSns(String userId); //마이페이지 조회
	
	List<AtchVO> selectSnsAtch(int snsNo); //게시글 삭제를 위한 이미지 아이디 찾기

	int insertSns(SnsVO vo); // sns등록

	int deleteSns(SnsVO vo); // sns수정

	int updateSns(SnsVO vo); // sns삭제
	
	int snsHitUpdate(int snNo); // 조회수 증가

}
