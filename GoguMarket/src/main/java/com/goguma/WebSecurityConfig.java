package com.goguma;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.goguma.mem.socialLogin.OAuthUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

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
	
	private final LoginSuccessHandler loginSuccessHandler;
	
//	private final KakaoLoginSuccessHandler kakaoLoginSuccessHandler; //카카오로그인
	
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
				.antMatchers("/my/**").hasAnyRole("USER", "ADMIN", "BIZ")
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
										 .successHandler(loginSuccessHandler).permitAll())
				
				// 소셜로그인 --------------------------------------------------------------------------------------------------
				.oauth2Login((oauthLogin)-> oauthLogin
									 .permitAll()
									 .successHandler(new KakaoLoginSuccessHandler())
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
