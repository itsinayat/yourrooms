package com.inayat.yourrooms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class YourroomsApplication extends SpringBootServletInitializer {  
	@Override  
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)   
	{  
	return application.sources(YourroomsApplication.class);  
	}  
	public static void main(String[] args)   
	{  
	SpringApplication.run(YourroomsApplication.class, args);  
	}  }
