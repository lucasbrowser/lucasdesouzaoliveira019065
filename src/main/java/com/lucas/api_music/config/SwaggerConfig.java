package com.lucas.api_music.config;

import java.util.Arrays;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi grupo() {
        return GroupedOpenApi.builder()
                .group("api-auth")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI documentacao() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("basic-bearer", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP).scheme("bearer")
                                .bearerFormat("bearer").in(SecurityScheme.In.HEADER).name("Authorization")))
                .info(apiInfo())
                .addSecurityItem(new SecurityRequirement()
                        .addList("basic-bearer", Arrays.asList("read", "write"))
                );
    }

    private Info apiInfo() {
        return new Info()
                .title("API - Auth")
                .description("Documentação Swagger - API Auth")
                .version("v1");

    }

}