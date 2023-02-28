package com.goguma.rsvt.service;

import java.util.List;

import com.goguma.rsvt.vo.RsvtRvVO;

public interface RsvtRvService {
	//가게 후기
	List<RsvtRvVO> rsvtReview(String bizNo);
}
