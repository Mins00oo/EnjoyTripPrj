package com.mycom.joytrip.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Autowired
	TestInterceptor testInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(testInterceptor)
				.addPathPatterns("/users/**")
				.addPathPatterns("/tours/stars/**")
				.addPathPatterns("/tours/users")
				.addPathPatterns("/tours/reviews/**")
				.excludePathPatterns("/login/**");
	}
}
