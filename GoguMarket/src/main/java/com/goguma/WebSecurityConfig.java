package com.goguma;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	//■■■■■■■■■■■■■■■■■■■■■■■■■■ 돈 타 치 !!!!!!!!!!!!!!!!!!!!!!!!! ■■■■■■■■■■■■■■■■■■■■■■■■■

	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
}
	
	@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(
				(requests) -> requests.antMatchers("/", "/home","/memberJoinForm", "/css/**", "/js/**", "/img/**", "/attech/**", "/fonts/**","/img/**","/lib/**","/vender/**","/style.css").permitAll()
								.anyRequest().authenticated())
		
				.formLogin((form) -> form.successHandler(new LoginSuccessHandler()))
				.logout((logout) -> logout.permitAll().logoutSuccessUrl("/")
									.invalidateHttpSession(true))
				.csrf().disable();

		return http.build();
	}

}
