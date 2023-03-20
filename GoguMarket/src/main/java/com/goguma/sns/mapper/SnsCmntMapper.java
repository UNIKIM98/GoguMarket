package com.goguma.sns.mapper;

import java.util.List;

import com.goguma.sns.vo.SnsCmntVO;
import com.goguma.sns.vo.SnsVO;


public interface SnsCmntMapper {
	public List<SnsCmntVO> SelectCmntlist(int snsNo);

	// 댓글 조회
	public List<SnsCmntVO> SelectCmnt(SnsCmntVO vo);
	
	List<SnsCmntVO>SelectUserCmnt(SnsCmntVO vo); //댓글 답글 조회

	// 댓긓 입력
	public int insertReply(SnsCmntVO vo);

	// 댓글 수정
	public int updateCmnt(SnsCmntVO vo);

	//  답글 삭제
	public int deleteRreply(SnsCmntVO vo);

}
