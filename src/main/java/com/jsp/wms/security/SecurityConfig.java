package com.jsp.wms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.Builder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);//uses BCrypt algorithm to encrypt the password
	}

	//httpsecurity build us to create filter chain
	@Bean
	public SecurityFilterChain  securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		//csrf is security pattern which will provide security
		return httpSecurity.csrf(csrf-> csrf.disable())
				.authorizeHttpRequests(authorize-> authorize.requestMatchers("/api/v1/register")
						.permitAll().anyRequest().authenticated())
				.formLogin(Customizer.withDefaults())
				.build();
	}

}
