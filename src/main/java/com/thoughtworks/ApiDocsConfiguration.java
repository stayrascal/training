package com.thoughtworks;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile("docs")
@Configuration
@EnableSwagger2
public class ApiDocsConfiguration {

    private static final String PROJECT_VERSION = "1.5.0";

    private static final String PROJECT_CONTACT = "thoughtworks.com";

    public Docket docket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Project Skeleton for Spring Boot Web Services")
                .description("The Spring Boot Web Services starter project provides a foundation to rapidly construct a RESTful web services application.")
                .contact(PROJECT_CONTACT)
                .version(PROJECT_VERSION)
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }
}
