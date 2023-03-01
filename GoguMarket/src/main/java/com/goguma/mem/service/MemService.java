package com.goguma.mem.service;

import com.goguma.mem.vo.MemVO;

public interface MemService {
	// ▶ 회원가입 ========================
	// 일반 회원가입
	int memberJoin(MemVO vo);

	// 아이디 중복확인
	int isIdCheck(String userId);

	// 닉네임 중복확인
	int isNickNmCheck(String nickNm);

	// 유저(일반유저) 한 명 조회
	MemVO selectUser(MemVO mVO);

	// ▶ 마이페이지 ========================
	// 우리동네 수정(회원 주소도 같이 변경됨)
	int updateDealArea(MemVO mVO);

}
