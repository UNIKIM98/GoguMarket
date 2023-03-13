package com.goguma.mem.socialLogin;

import java.sql.Date;
import java.util.Collections;

import javax.servlet.http.HttpSession;

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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuthUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final MemService memberService;
	private final HttpSession session;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {

		OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
		System.out.println("000000000000000000000000");

		OAuth2User oAuth2User = oAuth2UserService.loadUser(oAuth2UserRequest);
		System.out.println("111111111111111111");

		// 현재 진행중인 서비스를 구분하기 위해 문자열로 받음.
		// oAuth2UserRequest.getClientRegistration().getRegistrationId()에 값이 들어있다.
		// {registrationId='naver'} 이런식으로
		String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();
		System.out.println("22222222222222");

		// OAuth2 로그인 시 키 값이 된다. 구글은 키 값이 "sub"이고, 네이버는 "response"이고, 카카오는 "id"이다. 각각
		// 다르므로 이렇게 따로 변수로 받아서 넣어줘야함.
		String userNameAttributeName = oAuth2UserRequest.getClientRegistration().getProviderDetails()
				.getUserInfoEndpoint().getUserNameAttributeName();
		System.out.println("33333333333333333333333333");

		// OAuth2 로그인을 통해 가져온 OAuth2User의 attribute를 담아주는 of 메소드.
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,
				oAuth2User.getAttributes());
		System.out.println("44444444444444444444444444444");

		// 밑의 함수에 담기
		MemVO user = saveOrUpdate(attributes);
		System.out.println("555555555555555555555555555555555555555");

		// 세션보에 담기
		session.setAttribute("user", user);

		System.out.println(attributes.getAttributes() + "ddfsssssssss");

		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getUserSe())),
				attributes.getAttributes(), attributes.getNameAttributeKey());
	}

	private MemVO saveOrUpdate(OAuthAttributes attributes) {
		System.out.println(attributes.getProfile() + "ddddddddddddddddddddddddd>????????");

		// 보에 담아서 인써트
		MemVO vo = new MemVO();
		vo.setUserId(attributes.getId());
		vo.setUserPw(attributes.getPwd());
		vo.setUserNm(attributes.getName());
		vo.setMblTelno(attributes.getTel());
		vo.setBirthYmd((Date) attributes.getBirthDate());
		vo.setSocialToken(attributes.getToken());
		vo.setEml(attributes.getEmail());

		MemVO mvo = new MemVO();
		mvo = memberService.selectUser(vo);
		if (mvo == null) {
			memberService.memberJoin(vo);
			vo = memberService.selectUser(vo);
		} else {
			// 디비에 값이있음 uvo에만 담아준다.
			vo.setUserId(mvo.getUserId());
			vo.setUserPw(mvo.getUserPw());
		}

		return vo;
	}

}
