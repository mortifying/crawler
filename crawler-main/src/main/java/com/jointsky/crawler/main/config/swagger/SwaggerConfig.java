package com.jointsky.crawler.main.config.swagger;

import com.jointsky.crawler.quartz.common.utils.SwaggerUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return SwaggerUtils.initDocket();
    }

}
