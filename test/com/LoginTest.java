package com;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class LoginTest extends TestApplication{
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
    private FilterChainProxy springSecurityFilterChain;
	
	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain)
                .build();
	}

	@Test
	public void loginPageLoading() throws Exception 
	{
		mockMvc.perform(get("/login")).andExpect(status().isOk());
				

	}
	
	@Test
	public void registerPageLoading() throws Exception 
	{
		mockMvc.perform(get("/register")).andExpect(status().isOk());
				

	}
	
	

	@Test
	public void genuineLogin() throws Exception {
	    RequestBuilder requestBuilder = post("http://localhost:8080/login")
	            .param("username", "gowtham")
	            .param("password", "gowtham");
	    		 mockMvc.perform(requestBuilder)
	            .andDo(print())
	            .andExpect(redirectedUrl("/"));	
	            
	            
	}
	@Test
	public void wrongCredentials() throws Exception {
	    RequestBuilder requestBuilder = post("http://localhost:8080/login")
	            .param("username", "xyz")
	            .param("password", "xyz");
	    		 mockMvc.perform(requestBuilder)
	            .andDo(print())
	            .andExpect(redirectedUrl("/login-failure"));
	            
	}
	
	@Test
	public void unauthorizedAccess() throws Exception {
	   
	    
		mockMvc.perform(get("http://localhost:8080/menu")).andExpect(status().isUnauthorized());
		
	}
}