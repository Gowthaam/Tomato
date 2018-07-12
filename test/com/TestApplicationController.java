package com;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@WebAppConfiguration
public abstract class TestApplicationController extends TestApplication {

    protected MockMvc mvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

   
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

  
    protected void setUp(AppController controller) {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
} 