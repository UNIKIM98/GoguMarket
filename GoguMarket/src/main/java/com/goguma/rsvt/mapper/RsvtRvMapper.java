package com.goguma.rsvt.mapper;

import java.util.List;

import com.goguma.rsvt.vo.RsvtRvVO;

public interface RsvtRvMapper {
	//가게 후기
	List<RsvtRvVO> rsvtReview(String bizNo);
}
