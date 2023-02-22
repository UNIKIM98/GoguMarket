package com.goguma.mem.service;

import com.goguma.mem.vo.MemVO;

public interface MemService {
	// ▶ 일반 회원가입
	int memberJoin(MemVO vo);

	// ▶ 아이디 중복확인
	boolean isIdCheck(String userId);

	// ▶ 닉네임 중복확인
	boolean isNickNmCheck(String nickNm);
}
