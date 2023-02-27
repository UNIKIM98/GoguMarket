package com.goguma.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.goguma.common.mapper.CommonCodeMapper;
@Service
public interface CommonCodeService {

	List<CommonCodeMapper> codeList(String commonCode);

}
