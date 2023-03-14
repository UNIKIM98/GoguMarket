package com.goguma;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
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

//import com.goguma.mem.socialLogin.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
//    private final CustomOAuth2UserService customOAuth2UserService;
//------------------------------------------------------------------

	private final UsersService usersSrvice;
	private final DataSource dataSource;
	private final LoginSuccessHandler loginSuccessHandler;

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

	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests

				.antMatchers("/", "/home", "/goguma/**", "/css/**", "/attech/**", "/fonts/**", "/images/**", "/img/**",
						"/js/**", "/lib/**", "/scss/**", "/vendor/**", "/style.css")
				.permitAll().antMatchers("/my/**").hasAnyRole("USER", "ADMIN", "BIZ").antMatchers("/biz/**")
				.hasAnyRole("BIZ", "ADMIN").antMatchers("/admin/**").hasRole("ADMIN")
//				.anyRequest().authenticated()
				).rememberMe((remember) -> remember.tokenValiditySeconds(86400 + 43200).rememberMeParameter("remember-me")
				.tokenRepository(persistentTokenRepository()).userDetailsService(usersSrvice))
				.formLogin((form) -> form.loginPage("/goguma/login").loginProcessingUrl("/login_proc")
						.successHandler(loginSuccessHandler).permitAll())

//				.oauth2Login((oauthLogin) -> oauthLogin.permitAll().successHandler(new KakaoLoginSuccessHandler())
//						.userInfoEndpoint().userService(customOAuth2UserService))

				.logout((logout) -> logout.permitAll().logoutSuccessUrl("/goguma/dealMain").invalidateHttpSession(true))
				.csrf().disable();

		return http.build();
	}

}
