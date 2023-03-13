package com.goguma.mem.socialLogin;

import java.io.Serializable;

import com.goguma.mem.vo.MemVO;

import lombok.Data;

@Data
public class SessionUser implements Serializable {

	private String userId;
	private String eml;
	private String userSe;
	private String userNm;
	private String dealArea;

	public SessionUser(MemVO user) {
		this.userId = user.getUserId();
		this.eml = user.getEml();
		this.userSe = user.getUserSe();
		this.userNm = user.getUserNm();
		this.dealArea = user.getDealArea();

	}
}
