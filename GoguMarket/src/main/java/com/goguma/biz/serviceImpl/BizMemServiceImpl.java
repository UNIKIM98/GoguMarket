package com.goguma.biz.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.goguma.biz.mapper.BizMemMapper;
import com.goguma.biz.service.BizMemService;
import com.goguma.biz.vo.BizDangolVO;
import com.goguma.biz.vo.BizMemVO;
import com.goguma.biz.vo.BizSearchVO;

@Service
public class BizMemServiceImpl implements BizMemService {

	@Autowired
	private BizMemMapper map;
	
	//가게 목록 전체조회
	@Override
	public List<BizMemVO> getBizList() {
		return map.getBizList();
	}

	//가게 상세정보(book02~05전체)
	@Override
	public BizMemVO bizInfo(String bizNo) {
		return map.bizInfo(bizNo);
	}

	//가게 목록 페이징
	@Override
	public List<BizMemVO> bizListPage(BizSearchVO bvo) {
		return map.bizListPage(bvo);
	}

	//페이지 수
	@Override
	public int bizListCnt(BizSearchVO bvo) {
		return map.bizListCnt(bvo);
	}

	//단골 카운팅
	@Override
	public List<BizMemVO> BizDangolCnt() {
		return map.BizDangolCnt();
	}

	//리뷰 카운팅
	@Override
	public List<BizMemVO> BizReviewCnt() {
		return map.BizReviewCnt();
	}

	//사진
	@Override
	public List<BizMemVO> bizImgList() {
		return map.bizImgList();
	}
	
	//상세사진
	@Override
	public List<BizMemVO> bizDetailImg(String bizNo) {
		return map.bizDetailImg(bizNo);
	}


	@Override
	public String selectBizNo(String userId) {
		// TODO Auto-generated method stub
		return map.selectBizNo(userId);
	}

	//가게등록
	@Override
	public int bizInsert(BizMemVO vo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); //기존 권한
		List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities()); 
		
		System.out.println("권한목록 ===");
		System.out.println(updatedAuthorities);
		
		updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_BIZ"));
		
		Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(newAuth);
		
		return map.bizInsert(vo);

	}


	
	
}