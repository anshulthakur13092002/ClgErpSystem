package com.example.collegeerp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
public class CollegeErpApplication implements WebMvcConfigurer, WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CollegeErpApplication.class, args);
	}



	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addListener(HttpSessionEventPublisher.class);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**")
				.addResourceLocations("classpath:/static/");
	}
}
