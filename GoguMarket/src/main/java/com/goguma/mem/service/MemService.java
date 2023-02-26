package com.goguma.mem.service;

import com.goguma.mem.vo.MemVO;

public interface MemService {
	// 회원가입 관련 ========================
	// ▶ 일반 회원가입
	int memberJoin(MemVO vo);

	// ▶ 아이디 중복확인
	int isIdCheck(String userId);

	// ▶ 닉네임 중복확인
	int isNickNmCheck(String nickNm);

	// ▶ 유저(일반유저) 한 명 조회
	MemVO selectUser(MemVO mVO);

}
