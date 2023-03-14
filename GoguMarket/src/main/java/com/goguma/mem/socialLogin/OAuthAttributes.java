package com.goguma.mem.socialLogin;

import java.sql.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goguma.mem.vo.MemVO;

import lombok.Data;

@Data
public class OAuthAttributes {

	private final Map<String, Object> attributes; //로그인시 넘어오는 kakao attributes
	private final String nameAttributeKey;
	private final String userId; // 아이디
	private final String userNm; // 이름
	private final String userPw; // 비밀번호
	private final String mblTelno;
	private final String nickNm; // 닉네임
	private final String eml; // 이메일
	private final String atchPath;// 프로필사진
	private final String socialToken; // 소셜토큰
	private final String rfshToken; // 리프레시 토큰
	

	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String userId, String userNm, String userPw, String mblTelno, String nickNm, String eml,
			String atchPath, String socialToken, String rfshToken) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.userId = userId;
		this.userNm = userNm;
		this.userPw = userPw;
		this.mblTelno = mblTelno;
		this.nickNm = nickNm;
		this.eml = eml;
		this.atchPath = atchPath;
		this.socialToken = socialToken;
		this.rfshToken = rfshToken;
	}

	// 해당 로그인인 서비스가 kakao인지 google인지 구분하여, 알맞게 매핑을 해주도록 합니다.
	// 여기서 registrationId는 OAuth2 로그인을 처리한 서비스 명("google","kakao","naver"..)이 되고,
	// userNameAttributeName은 해당 서비스의 map의 키값이 되는 값이됩니다. {google="sub", kakao="id",
	// naver="response"}
	public static OAuthAttributes of(String registrationId, String userNameAttributeName,
			Map<String, Object> attributes) {
		return ofKakao(userNameAttributeName, attributes);
	}

	// 카카오
	private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account"); 
		Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile"); 
		
		System.out.println("카카오 어카운트(attributes)");
		System.out.println(kakao_account);
		
		System.out.println("카카오 프로필");
		System.out.println(profile);
		
		String email = (String) kakao_account.get("email");
		String userId = "kakao"+"_"+email.split("@")[0];
		String uuid = UUID.randomUUID().toString().substring(0, 6);

		return new OAuthAttributes(attributes, 
									userNameAttributeName, 
									userId,
									(String) profile.get("nickname"), 
									uuid,
									(String) kakao_account.get("mobile"),
									(String) profile.get("nickname"),
									email, 
									(String) profile.get("profile_image_url"), 
									"", 
									"");
	}
}