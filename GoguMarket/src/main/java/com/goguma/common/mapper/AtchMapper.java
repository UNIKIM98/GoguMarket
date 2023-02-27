package com.goguma.common.mapper;

import org.springframework.web.multipart.MultipartFile;

public interface AtchMapper {
	// ▶ atchId 구하는 쿼리문
	int selectAtchId();

	// ▶ 파일 다중 업로드 > atchId 리턴
	int fileUpload(MultipartFile file);

	// ▶ 파일 삭제

	// ▶ 파일 셀렉트
}
