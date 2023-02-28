package com.goguma.rsvt.service;

import java.util.List;

import com.goguma.rsvt.vo.BizMenuVO;

public interface BizMenuService {
	
	//가게 메뉴
	List<BizMenuVO> bizMenu(String bizNo);
}
