package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 * This class is used for Initializing the spring boot application 
 * @author gowtham
 *
 */
@EnableJpaRepositories(basePackages = "com")
@SpringBootApplication
public class TomatoApplication extends SpringBootServletInitializer {
	
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
        return app.sources(TomatoApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(TomatoApplication.class, args);
	}
}