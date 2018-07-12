package com;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
	
	AppController app;
	
	@Before
public void setup() {
mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain)
            .build();
logic = new Logic();
app = new AppController();
app.hotelName="ohris";
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


@Test	
public void addItemBillTest()
{
	OrderDetails od = new OrderDetails();
	od.setItem("pizza");
	od.setPrice(200);
	od.setQuantity(5);
	app.addItemLogic(od);
	assertEquals(app.bill,1000);
}

@Test	
public void removeItemBillTest()
{
	OrderDetails od = new OrderDetails();
	od.setItem("pizza");
	od.setPrice(200);
	od.setQuantity(5);
	app.addItemLogic(od);
	od.setQuantity(2);
	app.removeItemLogic(od);
	assertEquals(app.bill,0);
}


@Test	
public void addItemCartTest()
{
	OrderDetails od = new OrderDetails();
	od.setItem("pizza");
	od.setPrice(200);
	od.setQuantity(5);
	app.addItemLogic(od);

	for(OrderDetails o : app.order.get("ohris") )
		{assertEquals(o.item,"pizza");
		assertEquals(o.quantity,5);
		assertEquals(o.price,200);
		}
		
}

@Test	
public void removeItemCartTest()
{
	OrderDetails od = new OrderDetails();
	od.setItem("pizza");
	od.setPrice(200);
	od.setQuantity(5);
	app.addItemLogic(od);
	app.removeItemLogic(od);
	assertEquals(app.order.get("ohris").isEmpty(),true);	
}

@Test
public void unauthorizedAccess() throws Exception {
    
	mockMvc.perform(get("http://localhost:8080/menu")).andExpect(status().isUnauthorized());
	
}


@Test
public void loginPageLoading() throws Exception 
{
	mockMvc.perform(get("/login")).andExpect(status().isOk());
			

}

}

