package com.idexx.animana.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * Configure automatic generation of the OpenAPI 3 specification docs for Media Search
 * API
 * 
 * @author mlazic
 * @since 1.0
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30).select()
				.apis(RequestHandlerSelectors.basePackage("com.idexx.animana")).paths(PathSelectors.any()).build();
	}
}