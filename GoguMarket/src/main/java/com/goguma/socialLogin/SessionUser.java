package com.goguma.socialLogin;

import java.io.Serializable;

import com.goguma.mem.vo.MemVO;

import lombok.Data;

@Data
public class SessionUser implements Serializable {
	
	private String id;
	private String role;
	
	public SessionUser(MemVO user) {
		this.id = user.getUserId();
		this.role = user.getUserSe();
	}
	
	public SessionUser() {}
}
