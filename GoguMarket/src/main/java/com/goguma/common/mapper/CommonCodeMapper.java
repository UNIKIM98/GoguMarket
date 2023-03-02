package com.goguma.common.mapper;

import java.util.List;

import com.goguma.common.vo.CommonCodeVO;

public interface CommonCodeMapper {
	List<CommonCodeVO> codeList(String commonCode);
	
}
