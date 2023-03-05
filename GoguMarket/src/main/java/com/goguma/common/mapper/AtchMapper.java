package com.goguma.common.mapper;

import java.util.List;

import com.goguma.common.vo.AtchVO;

public interface AtchMapper {
	// ▶ atchId 구하는 쿼리문
	int selectAtchId();

	// ▶ 파일 셀렉트
	List<AtchVO> selectAtch(int atchId);

	// ▶ 파일 한 개 셀렉트
	AtchVO selectFile(AtchVO atchVO);

	// ▶ 파일 다중 업로드 > atchId 리턴
	int insertFile(AtchVO atchVO);

	// ▶ 파일 삭제
	int deleteFile(AtchVO atchVO);


}
