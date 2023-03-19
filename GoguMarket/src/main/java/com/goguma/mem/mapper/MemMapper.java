package com.goguma.mem.mapper;

import java.util.List;

import com.goguma.mem.vo.MemVO;

public interface MemMapper {
	// 재엽 관리자 ====================================
	// 유저(일반유저) 전체 조회
	List<MemVO> selectMemberList(MemVO vo);

	int updateStts(MemVO vo);

	int deleteMember(MemVO vo);

	int getcountTotal(MemVO vo);

	// 채은 ====================================
	// ❤️ 일반 회원가입
	int memberJoin(MemVO vo);

	// ❤️ 아이디 중복확인
	int isIdCheck(String userId);

	// ❤️ 이메일 중복 확인
	int isEmlCheck(String eml);

	// ❤️ 닉네임 중복확인
	int isNickNmCheck(String nickNm);

	// ❤️ 유저(일반유저) 한 명 조회
	MemVO selectUser(MemVO mVO);

	// ❤️ 회원 정보 수정
	int updateUser(MemVO mVO);

	// ❤️ 우리동네 수정(회원 주소도 같이 변경됨)
	int updateDealArea(MemVO mVO);

	// ❤️ 비밀번호 수정
	int updateUserPw(MemVO mVO);

	// ❤️ 권한변경(일반 > 비즈)
	int updateUserSe(MemVO mVO);

}
