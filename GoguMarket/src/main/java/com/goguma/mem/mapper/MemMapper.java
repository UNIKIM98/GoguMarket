package com.goguma.mem.mapper;

import com.goguma.mem.vo.MemVO;

public interface MemMapper {
	// ▶ 일반 회원가입
	int memberJoin(MemVO vo);

	// ▶ 아이디 중복확인
	boolean isIdCheck(String userId);

	// ▶ 닉네임 중복확인
	boolean isNickNmCheck(String nickNm);

}
