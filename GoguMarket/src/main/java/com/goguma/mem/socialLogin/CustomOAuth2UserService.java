package com.goguma.mem.socialLogin;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.goguma.mem.service.MemService;
import com.goguma.mem.vo.MemVO;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	@Autowired
	private MemService memService;

	@Autowired
	private HttpSession httpSession;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();

		OAuth2User oAuth2User = oAuth2UserService.loadUser(oAuth2UserRequest);
		// 현재 진행중인 서비스를 구분하기 위해 문자열로 받음.
		// oAuth2UserRequest.getClientRegistration().getRegistrationId()에 값이 들어있다.
		// {registrationId='naver'} 이런식으로
		String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();

		// OAuth2 로그인 시 키 값이 된다. 구글은 키 값이 "sub"이고, 네이버는 "response"이고, 카카오는 "id"이다. 각각
		// 다르므로 이렇게 따로 변수로 받아서 넣어줘야함.
		String userNameAttributeName = oAuth2UserRequest.getClientRegistration().getProviderDetails()
				.getUserInfoEndpoint().getUserNameAttributeName();

		// OAuth2 로그인을 통해 가져온 OAuth2User의 attribute를 담아주는 of 메소드.
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,
				oAuth2User.getAttributes());

		MemVO user = saveOrUpdate(attributes);
		httpSession.setAttribute("user", user);

		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
				attributes.getAttributes(), attributes.getNameAttributeKey());
	}

	// 혹시 이미 저장된 정보라면, update 처리
	private MemVO saveOrUpdate(OAuthAttributes attributes) {
		MemVO mVO = new MemVO();
		mVO.setUserId(attributes.getUserId());

		MemVO nVO = new MemVO();
		mVO.setUserSe("USER");
		mVO.setUserPw(attributes.getUserPw());
		mVO.setUserNm(attributes.getUserNm());
		mVO.setNickNm(attributes.getNickNm());
		mVO.setAddr("");// 주소
		mVO.setDealArea("");// 거래지역
		mVO.setMblTelno(attributes.getMblTelno());
		mVO.setAtchPath(attributes.getAtchPath());
		
		nVO = memService.selectUser(mVO);//가입여부만 체크(id)
		
		if (nVO == null) {
			mVO.setEml(attributes.getEml());
			memService.memberJoin(mVO);
		}
		mVO.setEml(attributes.getEml());
		
		return mVO;
	}
}
