package com.goguma.mem.service;

import java.util.List;

import com.goguma.mem.vo.MemVO;

public interface MemService {
	// ▷ 회원가입 ========================
	// 일반 회원가입
	int memberJoin(MemVO vo);

	// 아이디 중복확인
	int isIdCheck(String userId);

	// 닉네임 중복확인
	int isNickNmCheck(String nickNm);

	// ====================================관리자 사용

	// 유저(일반유저) 전체 조회
	List<MemVO> selectMemberList(MemVO vo);

	int updateStts(MemVO vo);
	
	int deleteMember(MemVO vo);
	


	// ====================================관리자 사용
	// 유저(일반유저) 한 명 조회
	MemVO selectUser(MemVO mVO);

	// ▷ 마이페이지 ========================
	// 회원 정보 수정
	int updateUser(MemVO mVO);

	// 우리동네 수정(회원 주소도 같이 변경됨)
	int updateDealArea(MemVO mVO);

}
