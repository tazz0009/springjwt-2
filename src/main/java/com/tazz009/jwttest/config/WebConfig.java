package com.tazz009.jwttest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/login")
//        	.allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000");
//	}
	
}
