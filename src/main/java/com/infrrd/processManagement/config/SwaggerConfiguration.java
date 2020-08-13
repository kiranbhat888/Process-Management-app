package com.infrrd.processManagement.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration @EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()                
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/process/*"))    
                .build()
                .apiInfo(apiInfo()); 
    }

	private ApiInfo apiInfo() {
		// TODO Auto-generated method stub
        return new ApiInfo(
                "Process Management App", 
                "Handles only running process at a time", 
                "abcd", 
                "efgh", 
                new Contact("Kiran Bhat", "www.example.com", "kiranbhat888@gmail.com"), 
                "", "www.google.com", Collections.emptyList());
	}

}
