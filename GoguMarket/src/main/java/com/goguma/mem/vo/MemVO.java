package com.goguma.mem.vo;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MemVO implements UserDetails {
	

	// => 관리자 사용
	private String userSe;
	private String searchKey;
	private String search;
	private String userStts;
	private int userNowPage;

	private int first;
	private int last; // 페이징 사용 - 마지막페이지

	private String userId;
	private String userPw;
	private String userNm;
	private String gender;
	private String nickNm;
	private String addr;
	private String dealArea;
	private String eml;
	private String pwCkPage; //비밀번호 확인할 때 사용할 페이지

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthYmd;
	private String mblTelno;
	private String bankNm;
	private String actNo;
	private String rcmdCode;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joinYmd;
	private String socialToken;
	private String rfshToken;
	private String socialSe;
	private String atchNm;
	private String atchPath;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(this.userSe));
	}

	@Override
	public String getPassword() {
		return this.userPw;
	}

	@Override
	public String getUsername() {
		return this.userId;
	}

	@Override 
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override 
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override // 접근금지 회원 (userSe > 1(정지))
	public boolean isEnabled() {
		return this.userStts.equals("1") ? false : true;
	}

}
