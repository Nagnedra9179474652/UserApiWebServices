package com.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.user.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private Environment environment;
	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public WebSecurityConfig(Environment environment, UserService userService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.environment = environment;
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable();
		/*
		 * http.authorizeRequests().antMatchers("/**").hasIpAddress(environment.
		 * getProperty("geteway_ip")).and() .addFilter(getAuthenticationFilter());
		 */
		http.authorizeRequests().antMatchers("/**").permitAll().and()
		.addFilter(getAuthenticationFilter());
		http.headers().frameOptions().disable();
	}

	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authenticationFilter= new AuthenticationFilter(environment, userService, authenticationManager());
	//	authenticationFilter.setFilterProcessesUrl(""); //write some custom url path here
		return authenticationFilter;
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}

}
