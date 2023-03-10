package com.goguma.auct.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goguma.auct.mapper.AuctMapper;
import com.goguma.auct.service.AuctService;
import com.goguma.auct.vo.AuctVO;
import com.goguma.common.vo.AtchVO;

@Service
public class AuctServiceImpl implements AuctService{
	@Autowired
	AuctMapper auctMapper;
	
	

	@Override
	public List<AuctVO> getAuctList() {
		// 경매 리스트 전체 조회
		return auctMapper.getAuctList();
	}
	
	@Override
	public AuctVO getAuct(AuctVO vo) {
		// 단건 조회
		return auctMapper.getAuct(vo);
		
	}
	
	@Override
	public int insertAuct(AuctVO vo) {
		// 등록
		return auctMapper.insertAuct(vo);
	}

	@Override
	public int deleteAuct(AuctVO vo) {
		// 삭제
		return auctMapper.deleteAuct(vo);
	}

	@Override
	public int auctHitUpdate(int auctNo) {
		// 조회수 증가
		return auctMapper.auctHitUpdate(auctNo);
	}

	@Override
	public AuctVO selectAuctNo(int auctNo) {
		// 삭제에 쓰는 int auctNo
		return auctMapper.selectAuctNo(auctNo);
	}

	@Override
	public List<AtchVO> selectAuctAtch(int auctNo) {
		// 단건조회 모든 이미지
		return auctMapper.selectAuctAtch(auctNo);
	}

	@Override
	public List<AuctVO> selectUserId(String userId) {
		// 마이페이지에 쓰는 String auctId
		return auctMapper.selectUserId(userId);
	}
}
