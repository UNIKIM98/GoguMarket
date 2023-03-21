package com.goguma.deal.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.deal.mapper.DealReviewMapper;
import com.goguma.deal.mapper.DealRvVoteMapper;
import com.goguma.deal.service.DealReviewService;
import com.goguma.deal.vo.DealReviewVO;
import com.goguma.deal.vo.DealRvVoteVO;
import com.goguma.mem.mapper.PointMapper;
import com.goguma.mem.vo.PointVO;

@Service
public class DealReviewServiceImpl implements DealReviewService {

	@Autowired
	private DealReviewMapper map;

	@Autowired
	private DealRvVoteMapper vmap;
	
	@Autowired
	private PointMapper pmap;
	


	@Override
	public int insertDealRv(DealReviewVO vo, List<String> vtList, String userId) {
		// 리뷰 작성
		int rvno = map.selectRvNo(); // 큰 그룹번호 받아주기 2 3
		vo.setRvNo(rvno); //3		
		map.insertDealRv(vo); //3
		
		//vote 추가 ㄱㄱ~~
		int cnt = 0;
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
		
		// 포인트는 글 건수당 한번 들어가야함!!
		PointVO pvo = new PointVO();
		pvo.setPoint(100);
		pvo.setPointMthd("거래후기");
		pvo.setUserId(userId); // 구매자 아이뒤집어넣어야.. 그럼 저거 인서드딜리뷰에 스트링 구매자아뒤도 집어처넣?
		pmap.insertPoint(pvo);
		
		// 리턴에 .. 포인트 부여
		return cnt;
	}

	// 수정 필요
	@Override
	public int selectRvNo() {
		// TODO Auto-generated method stub
		return map.selectRvNo();
	}

	@Override
	public List<DealReviewVO> selectGetRv(DealReviewVO vo) {
		// TODO Auto-generated method stub
		return map.selectGetRv(vo);
	}

	@Override
	public List<DealReviewVO> selectSendRv(DealReviewVO vo) {
		// TODO Auto-generated method stub
		return map.selectSendRv(vo);
	}

	@Override
	public List<DealReviewVO> selectNotSendRv(DealReviewVO vo) {
		// TODO Auto-generated method stub
		return map.selectNotSendRv(vo);
	}

	@Override
	public int getcountTotal(DealReviewVO rvVO) {
		// 페이지수 계산
		return map.getcountTotal(rvVO);
	}

	@Override
	public List<Map> getDealRv(DealReviewVO rvVO) {
		// TODO Auto-generated method stub
		return map.getDealRv(rvVO);
	}

}
