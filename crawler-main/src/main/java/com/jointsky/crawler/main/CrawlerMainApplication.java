package com.jointsky.crawler.main;

import com.jointsky.crawler.quartz.common.utils.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@ServletComponentScan
@ComponentScan(basePackages = "com.jointsky.crawler")
@MapperScan("com.jointsky.crawler.entity.mapper")
public class CrawlerMainApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(CrawlerMainApplication.class, args);
        SpringContextUtil.setApplicationContext(applicationContext);
    }
}
