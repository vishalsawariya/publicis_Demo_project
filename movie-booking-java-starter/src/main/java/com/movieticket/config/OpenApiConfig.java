package com.movieticket.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("XYZ Movie Booking API").version("v1").description("B2B & B2C movie booking APIs"))
                .externalDocs(new ExternalDocumentation().description("GitHub/Docs placeholder"));
    }
}