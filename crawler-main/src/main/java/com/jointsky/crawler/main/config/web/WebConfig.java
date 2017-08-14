package com.jointsky.crawler.main.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                //.allowedMethods("PUT", "DELETE","POST","GET","OPTIONS")
                .allowedHeaders("*")
                //.exposedHeaders("header1", "header2")
                .allowCredentials(true).maxAge(3600);
    }
}