package com;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.model.User;
import com.repository.UsersRepository;

public class BasicTest extends TestApplication {
	Logic logic;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
    private FilterChainProxy springSecurityFilterChain;
	
	private MockMvc mockMvc;

	@Autowired
	UsersRepository usersrepository;
	
	@Before
public void setup() {
mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain)
            .build();
logic = new Logic();
//mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
}



@Test	
public void forgotReplyTest()
{
	User u = new User();
	u.setUname("keerthan");
	assertEquals("Hi"+u.getUname().toUpperCase()+"! Password has been sent to your registered Mail.",logic.forgetReply(u,usersrepository));
}

@Test	
public void forgotReplyTestFail()
{
	User u = new User();
	u.setUname("rajesh");
	assertEquals("Username not found ! Please Register",logic.forgetReply(u,usersrepository));
}

@Test	
public void addUserTestFail()
{
	User u = new User();
	u.setUname("keerthan");
	assertEquals("register",logic.addUser(u,usersrepository));
}



}

