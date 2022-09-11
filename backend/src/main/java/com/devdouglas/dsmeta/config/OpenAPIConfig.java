package com.devdouglas.dsmeta.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
            .info(
                new Info()
                    .title("API for Sales business project.")
                    .description("API Sales")
                    .version("1.0.0")
            );
    }
}
