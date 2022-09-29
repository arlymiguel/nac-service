package com.nace.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Optional;

@Configuration
public class SpringdocConfig {

    private final String appUrl;
    private final Optional<String> serviceName;

    public SpringdocConfig() {
        this.appUrl = "http://localhost:8080/";
        this.serviceName = Optional.of("nac-service");
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .servers(Collections
                        .singletonList(new Server().url(appUrl)))
                .components(new Components())
                .info(new Info().title(serviceName.orElse("") + " API Docs")
                        .description(serviceName.orElse("") + " Service REST API documentation"));
    }

    /*
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.codmind.swaggerapi.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                ;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Order Service API",
                "Order Service API Description",
                "1.0",
                "http://codmind.com/terms",
                new Contact("Codmind", "https://codmind.com", "apis@codmind.com"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
    */

}
