package com.goguma.common.service;

import java.util.List;

import com.goguma.common.vo.CommonCodeVO;

public interface CommonCodeService {

	// 공통코드 이용하여 카테고리 불러오기
	List<CommonCodeVO> codeList(String commonCode);
}
