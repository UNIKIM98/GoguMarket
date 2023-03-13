package com.goguma;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.goguma.mem.socialLogin.OAuthUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	 	@Autowired
	    private OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService;


	// ===================================================================================================================
	// ❤️ 로그인 유지(정배오빠)
//	@Autowired
//	DataSource dataSource;

//	@Bean
//	public PersistentTokenRepository PersistentTokenRepository() {
//		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
//		repo.setDataSource(dataSource);
//		return repo;
//	}
	
	// ===================================================================================================================
	// ❤️ 소셜로그인(태경언니)
	private final OAuthUserService userService;
	
	// ===================================================================================================================
	// ❤️ 비밀번호 암호화
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// ===================================================================================================================
	// ❤️ static 권한 설정 해제(절대경로 필요)
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	// ====================================================================================================================
	// ❤️ 시큐리티 필터 체인 설정
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				
				// 경로별 권한 설정 --------------------------------------------------------------------------------------------
				.antMatchers("/", "/home", "/goguma/**", "/css/**", "/attech/**", "/fonts/**", "/images/**", "/img/**",
							"/js/**", "/lib/**", "/scss/**", "/vendor/**", "/style.css").permitAll()
				.antMatchers("/my/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/biz/**").hasAnyRole("BIZ", "ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
//				.anyRequest().authenticated()
				)
				
				// 로그인 유지 ------------------------------------------------------------------------------------------------
				//.rememberMe((remember) -> remember.tokenValiditySeconds(86400 + 43200) // 토큰 유효기간(36h)
				//							.rememberMeParameter("remember-me").tokenRepository(PersistentTokenRepository()))
		
				// 커스텀로그인 ------------------------------------------------------------------------------------------------
				.formLogin((form) -> form.loginPage("/goguma/login")
										 .loginProcessingUrl("/login_proc")
										 .successHandler(new LoginSuccessHandler()).permitAll())
				
				// 소셜로그인 --------------------------------------------------------------------------------------------------
				.oauth2Login((oauthLogin)-> oauthLogin
								     .loginPage("/goguma/login/oauth")
								     .permitAll()
								     .successHandler(new LoginSuccessHandler()) // 💙 커스텀 로그인 석세스 헨들러 따로 주신 이유는 몰까..
								     .userInfoEndpoint()
								     .userService(userService))
				
				// 로그아웃 ----------------------------------------------------------------------------------------------------
				.logout((logout) -> logout.permitAll().logoutSuccessUrl("/goguma/dealMain").invalidateHttpSession(true))
				.csrf().disable();

		return http.build();
	}

	// =======================================================================================================================
	// ❤️ 로그인 실패 핸들러

}
