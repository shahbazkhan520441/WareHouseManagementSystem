package com.jsp.wms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jsp.wms.filter.ClientApiKeyFilter;
import com.jsp.wms.repository.ClientRepository;

import lombok.Builder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Bean
	 PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);//uses BCrypt algorithm to encrypt the password
	}
	@Autowired
	ClientRepository clientRepository;

	//httpsecurity build us to create filter chain
	@Bean
	@Order(2)
	 SecurityFilterChain  securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		//csrf is security pattern which will provide security
		return httpSecurity.csrf(csrf-> csrf.disable())
				.authorizeHttpRequests(authorize-> authorize.requestMatchers("/api/v1/register" , "/login/**","/api/v1/clients","/api/v1/clients/{clientId}")
						.permitAll().anyRequest().authenticated())
				.formLogin(Customizer.withDefaults())
				.build();
	}
	
	@Bean
	@Order(1)
	SecurityFilterChain clientRequestFilterChain(HttpSecurity httpSecurity) throws Exception {
      return httpSecurity.csrf(AbstractHttpConfigurer::disable)
			  .securityMatchers(matcher->matcher.requestMatchers("/api/v1/client/**"))
			  .authorizeHttpRequests(authorize-> authorize.anyRequest().permitAll())
			  .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			  .addFilterBefore(new ClientApiKeyFilter(clientRepository), UsernamePasswordAuthenticationFilter.class)
			  .build();
	}

}
