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
	
	private String userId;

	// => 관리자 사용
	private String userSe;
	private String searchKey;
	private String search;
	private String userStts;
	private int userNowPage;

	private int first;
	private int last; // 페이징 사용 - 마지막페이지

	private String userPw;
	private String userNm;
	private String gender;
	private String nickNm;
	private String addr;
	private String dealArea;
	private String eml;

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
		// TODO Auto-generated method stub
		return Collections.singletonList(new SimpleGrantedAuthority(this.userSe));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.userPw;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
