package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This class sets the configuration of the spring security
 * @author gowtham
 *
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	 static final String LOGIN="/login";
	@Autowired 
	CustomAuth custauth;
	/**
	 * This function overrides the default spring security configure method
	 * It makes use of Custom authentication class and inMemoryAuthentication 
	 * for providing the security.
	 */
	@Override
	public void configure (AuthenticationManagerBuilder auth) throws Exception
	{
		
		auth.authenticationProvider(custauth);
        auth.inMemoryAuthentication()
            .withUser("gowtham")
            .password("gowtham")
            .roles("ADMIN");
		
	}
	
	/**
	 * It configures the authorization of access of various resources.
	 */
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	      http.csrf().disable();   
		 http.httpBasic()
	            .and()
	            .authorizeRequests()
	            .antMatchers(LOGIN,"/app-login","/login-failure","/register","/forgot-password").permitAll()
	            .anyRequest().authenticated()
	            .and()
	            .formLogin()
	            .loginPage(LOGIN)
	            //.loginProcessingUrl("/app-login")
	           // .usernameParameter("username").passwordParameter("password")
	            .defaultSuccessUrl("/")
	            .failureUrl("/login-failure")
	            .and()
	            .logout().logoutUrl("/app-logout")
	            .logoutSuccessUrl(LOGIN);
                
	            
	    }
	     
	
	
}
