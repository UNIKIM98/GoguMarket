package com.goguma.deal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.deal.mapper.DealRvVoteMapper;
import com.goguma.deal.service.DealRvVoteService;
import com.goguma.deal.vo.DealRvVoteVO;

@Service
public class DealRvVoteServiceImpl implements DealRvVoteService{

	@Autowired
	private DealRvVoteMapper map;
	
	@Override
	public List<DealRvVoteVO> getDealRvVote(String ntslId) {
		// 판매자아이디를 통해 후기 투표 리스트를 조회하기
		return map.getDealRvVote(ntslId);
	}

}
