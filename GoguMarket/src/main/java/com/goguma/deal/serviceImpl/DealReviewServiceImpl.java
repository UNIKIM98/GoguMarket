package com.goguma.deal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.deal.mapper.DealReviewMapper;
import com.goguma.deal.mapper.DealRvVoteMapper;
import com.goguma.deal.mapper.DealRvVoteMapper;
import com.goguma.deal.service.DealReviewService;
import com.goguma.deal.vo.DealReviewVO;
import com.goguma.deal.vo.DealRvVoteVO;

@Service
public class DealReviewServiceImpl implements DealReviewService {

	@Autowired
	private DealReviewMapper map;

	@Autowired
	private DealRvVoteMapper vmap;
	
	// 포인트매퍼~나중에 넣으세요~
	
	@Override
	public List<DealReviewVO> getDealRv(String ntslId) {
		//
		return map.getDealRv(ntslId);
	}

	@Override
	public int insertDealRv(DealReviewVO vo, List<String> vtList) {
		// 리뷰 작성
		int rvno = map.selectRvNo(); // 큰 그룹번호 받아주기 2 3
		vo.setRvNo(rvno); //3
		
		map.insertDealRv(vo); //3
		
		int cnt = 0;
		//vote 추가 ㄱㄱ~~
		if (vtList != null && !vtList.isEmpty()) {
			
			for (String vote : vtList) {
				DealRvVoteVO rvvo = new DealRvVoteVO();
				
				rvvo.setRvNo(rvno);
				//rvvtNo 는 시퀀스로 넣어줌
				rvvo.setRvvtCn(vote);
				
				vmap.insertDealRvVote(rvvo);
				cnt++;
			}
			
		}
		
		// 리턴에 .. 포인트 부여
		
		
		return cnt;
	}

	// 수정 필요
	@Override
	public int selectRvNo() {
		// TODO Auto-generated method stub
		return map.selectRvNo();
	}

}
