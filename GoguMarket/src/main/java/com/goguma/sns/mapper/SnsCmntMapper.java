package com.goguma.sns.mapper;

import java.util.List;

import com.goguma.sns.vo.SnsCmnt;


public interface SnsCmntMapper {
	public List<SnsCmnt> SelectCmntlist(int snsNo);

	// 댓글 조회
	public List<SnsCmnt> SelectCmnt(SnsCmnt vo);

	// 댓긓 입력
	public String inserReply(SnsCmnt vo);

	// 댓글 수정
	public String UpdateCmnt(SnsCmnt vo);

	// 댓글 삭제
	public String deleteCmnt(int CmntNo);

}
