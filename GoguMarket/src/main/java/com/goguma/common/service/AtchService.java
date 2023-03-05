package com.goguma.common.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.goguma.common.vo.AtchVO;

public interface AtchService {
	// ▶ atchId 구하는 쿼리문
	int selectAtchId();

	// ▶ 파일 셀렉트
	List<AtchVO> selectAtch(int atchId);

	// ▶ 파일 한 개 셀렉트
	AtchVO selectFile(AtchVO atchVO);

	// ▶ 파일 다중 업로드 > atchId 리턴
	int insertFile(List<MultipartFile> files);
	int insertFile(int atchId, List<MultipartFile> files); //파일 수정

	// ▶ 파일 삭제
	int deleteFile(List<AtchVO> atchVOs);
	
	

}
