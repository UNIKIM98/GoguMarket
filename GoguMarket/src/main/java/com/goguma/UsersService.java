package com.goguma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.goguma.mem.mapper.MemMapper;
import com.goguma.mem.vo.MemVO;

@Service
public class UsersService implements UserDetailsService {

	@Autowired
	MemMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		MemVO vo = new MemVO();

		vo.setUserId(username);
		vo = mapper.selectUser(vo);

		if (vo == null) {
			throw new UsernameNotFoundException("no user");
		}

		return vo;
	}

}
