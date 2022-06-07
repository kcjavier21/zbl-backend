package com.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable().authorizeRequests()
		.antMatchers("/home/**").permitAll()
		.antMatchers("/decode-token").permitAll()
		.antMatchers("/api/users/get/**").permitAll()
		.antMatchers("/api/players/get/**").permitAll()
		.antMatchers("/api/player-performances/get/**").permitAll()
		.antMatchers("/api/teams/get/**").permitAll()
		.antMatchers("/api/box-scores/get/**").permitAll()
		.antMatchers("/api/games/get/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.httpBasic();
	}
}