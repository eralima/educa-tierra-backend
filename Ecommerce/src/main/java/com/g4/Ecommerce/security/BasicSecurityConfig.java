package com.g4.Ecommerce.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter { 

	private @Autowired UserDetailsServiceImpl service;

	//ANOTACAO de DEPLOY para autenticar usuario "admin", sem precisar de cadastro=
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
auth.inMemoryAuthentication()
.withUser("admin").password(passwordEncoder().encode("admin")).authorities("ROLE_ADMIN");

auth.userDetailsService(service);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
		
	@Override
	protected void configure(HttpSecurity Http) throws Exception {
		Http.authorizeRequests().antMatchers(HttpMethod.POST, "/usuario/cadastro").permitAll()
		.antMatchers(HttpMethod.POST, "/usuario/login").permitAll() 
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf().disable();
	}
	
}
