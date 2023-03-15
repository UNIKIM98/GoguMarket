package com.goguma.common.mapper;

import java.util.List;
import java.util.Map;

public interface HomeMapper {
	//채은) 메인에서 사용할 mappers
	List<Map> selectHomeDeal();

	List<Map> selectHomeAuct();

	List<Map> selectHomeBiz(String ctgry);

	List<Map> selectHomeSns();
}
