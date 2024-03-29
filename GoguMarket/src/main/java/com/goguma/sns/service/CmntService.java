package com.goguma.sns.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.goguma.sns.vo.SnsCmntVO;
import com.goguma.sns.vo.SnsVO;



public interface CmntService {
	public List<SnsCmntVO> SelectCmntlist(int snsNo);

	// 댓글 조회
	public List<SnsCmntVO> SelectCmnt(SnsCmntVO vo);
	

	//댓긓 입력
	public int insertReply(SnsCmntVO vo);
	
	// 댓글 수정
	public int updateCmnt(SnsCmntVO vo);

	//  답글 삭제
	public int deleteRreply(SnsCmntVO vo);

	List<SnsCmntVO> SelectUserCmnt(SnsCmntVO vo);
}
