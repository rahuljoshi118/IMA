package com.cg.ima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


/**
 * When beans package is different
 * 
 * @SpringBootApplication(scanBasePackages="user")
 * 
 */

@SpringBootApplication //Combination of @Configuration + @ComponentScan + @EnableAutoConfiguration.
@EnableSwagger2WebMvc //It indicates that Swagger support should be enabled.
public class InhouseMarketplaceApplication {

	public static void main(String[] args) 
	{
		
		SpringApplication.run(InhouseMarketplaceApplication.class, args); //It is used to launch an application. 
	
	}// closing of main().
	
    @Bean //It is a method-level annotation and a direct analog of the XML <bean/> element.
    public Docket api() // It provides sensible defaults and convenience methods for configuration for Swagger.
    { 
    	return new Docket(DocumentationType.SWAGGER_2)
    			.select()
    			.apis(RequestHandlerSelectors.any())
    			.paths(PathSelectors.any())
    			.build();                                           
    }// closing of Docket api().


}// closing of InhouseMarketplaceApplication class.
