package com.fsega.animalbrokers.utils;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;

import static java.util.function.Predicate.not;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;
import static springfox.documentation.builders.RequestHandlerSelectors.withMethodAnnotation;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fsega.animalbrokers.controller"))
                .paths(PathSelectors.any())
                .apis(not(withClassAnnotation(ApiIgnore.class)))
                .apis(not(withMethodAnnotation(ApiIgnore.class)))
                .paths(not(PathSelectors.regex("/error")))
                .build()
                .directModelSubstitute(LocalDateTime.class, String.class)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Animal Brokers REST API")
                .description("REST API using Swagger and Spring")
                .license("Ovidiu Zakarias")
                .version("1.0")
                .contact(new Contact("Ovidiu Zakarias", null, "ovidiu.zakarias@gmail.com"))
                .build();
    }
}