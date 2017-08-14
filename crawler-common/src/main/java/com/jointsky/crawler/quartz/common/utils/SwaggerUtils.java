package com.jointsky.crawler.quartz.common.utils;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerUtils {

    private static ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger2 RESTful APIs")
                .termsOfServiceUrl("http://www.baidu.com/")
                .contact(new Contact("zhangxiongcolin", "https://github.com/zhangxiongcolin", "zhangxiongcolin@126.com"))
                .version("1.0")
                .build();
    }

    public static Docket initDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


}
