package com.goguma.rsvt.mapper;

import java.util.List;

import com.goguma.rsvt.vo.BizMenuVO;

public interface BizMenuMapper {
	//가게 메뉴
	List<BizMenuVO> bizMenu(String bizNo);
	
	//메뉴 하나 선택
	BizMenuVO selectBizMenuOne(int menuNo); 
	
	//메뉴삭제
	int deleteMenu(int menuNo); 
}
