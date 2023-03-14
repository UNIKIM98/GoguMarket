package com.goguma.common.service;

import java.util.List;
import java.util.Map;

public interface HomeService {
	//채은) 메인에서 사용할 services
	List<Map> selectHomeDeal();

	List<Map> selectHomeAuct();

	List<Map> selectHomeBiz(String ctgry);

	List<Map> selectHomeSns();
}
