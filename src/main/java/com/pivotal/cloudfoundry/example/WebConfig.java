package com.pivotal.cloudfoundry.example;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

@Component
public class WebConfig {

	 @Bean
	 public ViewResolver viewResolver() {
		 ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
	     MappingJacksonJsonView jacksonView = new MappingJacksonJsonView();
	     jacksonView.setPrefixJson(true);	     
	     contentNegotiatingViewResolver.setDefaultViews(Arrays.asList( (View) jacksonView));
	     return contentNegotiatingViewResolver;
	 }
	 	 
	 @Value("${tomcat.port:8080}")
	 private int _port;
	 
	 @Bean
	 public EmbeddedServletContainerFactory servletContainer() {
		 return new TomcatEmbeddedServletContainerFactory(_port);
	 }
}
