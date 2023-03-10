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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	// ======================
	// ▶ remember-me
	@Autowired
	DataSource dataSource;

	@Bean
	public PersistentTokenRepository PersistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

	// ======================
	// ▶ 비밀번호 암호화
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// ======================
	// ▶ css등 static에 들어있는 것들 적용 해제
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	// ======================
	// ▶ 시큐리티 필터 체인 설정
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.antMatchers("/", "/home", "/goguma/**", "/css/**", "/attech/**", "/fonts/**", "/images/**", "/img/**",
							"/js/**", "/lib/**", "/my/**", "/scss/**", "/vendor/**", "/style.css")
				.permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/biz/**").hasRole("BIZ")
				.antMatchers("/my/**").hasRole("USER")
				.anyRequest().authenticated())
				.rememberMe((remember) -> remember.tokenValiditySeconds(86400 + 43200) // 토큰 유효기간(36h)
											.rememberMeParameter("remember-me").tokenRepository(PersistentTokenRepository()))

				.formLogin((form) -> form.loginPage("/goguma/login")
										 .loginProcessingUrl("/login_proc")
										 .successHandler(new LoginSuccessHandler())
										 .permitAll()
						)
				.logout((logout) -> logout.permitAll().logoutSuccessUrl("/goguma/dealMain").invalidateHttpSession(true))
				.csrf().disable();

		return http.build();
	}

	// ======================
	// ▶ 로그인 실패시 핸들러 구현

}
