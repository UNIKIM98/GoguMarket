package com.goguma;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.goguma.mem.socialLogin.CustomOAuth2UserService;

//import com.goguma.mem.socialLogin.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    //===================================================================================================================

	private final UsersService usersSrvice;
	private final DataSource dataSource;
	private final LoginSuccessHandler loginSuccessHandler;
	private final FailureHandler failureHandler;

    //===================================================================================================================
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    //===================================================================================================================
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	
    //===================================================================================================================
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				
				// 경로에 따른 접근권한 부여
				.antMatchers("/", "/home", "/goguma/**", "/css/**", "/attech/**", "/fonts/**", 
							"/images/**", "/img/**", "/js/**", "/lib/**", "/scss/**", "/vendor/**", "/style.css", "/upload/**").permitAll()
				.antMatchers("/my/**").hasAnyRole("USER", "ADMIN", "BIZ")
				.antMatchers("/biz/**").hasAnyRole("BIZ", "ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated() //나머지 접근 금지
				
				// 로그인 유지(RememberMe)			
				).rememberMe((remember) -> remember.tokenValiditySeconds(86400 + 43200)// 토큰 유효기간(36시간) 
										    	   .rememberMeParameter("remember-me")
										    	   .tokenRepository(persistentTokenRepository())
										    	   .userDetailsService(usersSrvice))// 유저정보 제공
				
				// 커스텀 로그인
				.formLogin((form) -> form.loginPage("/goguma/login")
										 .loginProcessingUrl("/login_proc")
										 .successHandler(loginSuccessHandler)
										 .failureHandler(failureHandler)
										 .permitAll())

				// 카카오 로그인
				.oauth2Login((oauthLogin) -> oauthLogin.permitAll()
													   .successHandler(new KakaoLoginSuccessHandler())
													   .userInfoEndpoint()
													   .userService(customOAuth2UserService))
				
				// 로그아웃
				.logout((logout) -> logout.permitAll()
										  .logoutSuccessUrl("/goguma/logout")
										  .invalidateHttpSession(true)
										  .deleteCookies("JSESSION_ID", "remember-me"))

				.csrf().disable();

		return http.build();
	}

}
