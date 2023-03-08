package com.goguma;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(
				(requests) -> requests
						.antMatchers("/", "/home", "/newMem/**", "/css/**")
						.permitAll()
						.antMatchers("/admin/**").hasRole("ADMIN")
					    .antMatchers("/biz/**").hasRole("BIZ")
					    .antMatchers("/my/**").hasRole("USER")
					    .anyRequest().authenticated())
						

				.formLogin((form) -> form.successHandler(new LoginSuccessHandler()))
				.logout((logout) -> logout.permitAll().logoutSuccessUrl("/").invalidateHttpSession(true)).csrf()
				.disable();

		return http.build();
	}

}
