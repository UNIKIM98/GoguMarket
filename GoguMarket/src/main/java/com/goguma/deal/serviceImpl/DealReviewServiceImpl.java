package com.goguma.deal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.deal.mapper.DealReviewMapper;
import com.goguma.deal.service.DealReviewService;
import com.goguma.deal.vo.DealReviewVO;


@Service
public class DealReviewServiceImpl implements DealReviewService{

	@Autowired
	private DealReviewMapper map;
	
	@Override
	public List<DealReviewVO> getDealRv(int dlNo) {
		// TODO Auto-generated method stub
		return map.getDealRv(dlNo);
	}

}
