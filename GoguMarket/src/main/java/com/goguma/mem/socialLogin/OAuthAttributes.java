//package com.goguma.mem.socialLogin;
//
//import java.util.Map;
//
//import com.goguma.mem.vo.MemVO;
//
//import lombok.Data;
//@Data
//public class OAuthAttributes {
//	
//    private final Map<String, Object> attributes;
//    private final String nameAttributeKey;
//    private final String name;
//    private final String email;
//    private final String picture;
//    private final String userId;
//    private final String id;
//
//    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture, String userId, String id) {
//        this.attributes = attributes;
//        this.nameAttributeKey = nameAttributeKey; //이게 id
//        this.name = name;
//        this.email = email;
//        this.picture = picture;
//        this.userId = userId;
//        this.id = id;
//    }
//
//
//    // 해당 로그인인 서비스가 kakao인지 google인지 구분하여, 알맞게 매핑을 해주도록 합니다.
//    // 여기서 registrationId는 OAuth2 로그인을 처리한 서비스 명("google","kakao","naver"..)이 되고,
//    // userNameAttributeName은 해당 서비스의 map의 키값이 되는 값이됩니다. {google="sub", kakao="id", naver="response"}
//    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
//            return ofKakao(registrationId, userNameAttributeName, attributes);
//    }
//    
//    // 카카오
//    private static OAuthAttributes ofKakao(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
//        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");  // 카카오로 받은 데이터에서 계정 정보가 담긴 kakao_account 값을 꺼낸다.
//        Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");   // 마찬가지로 profile(nickname, image_url.. 등) 정보가 담긴 값을 꺼낸다.
//
//        
//        
//        
//        
//        return new OAuthAttributes(attributes,
//                userNameAttributeName,
//                (String) profile.get("nickname"),
//                (String) kakao_account.get("email"),
//                (String) profile.get("profile_image_url"),profile.get("id"),);
//    }
//}