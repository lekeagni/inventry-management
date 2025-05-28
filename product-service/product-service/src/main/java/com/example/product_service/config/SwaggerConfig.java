package com.example.product_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("test APIs REST with swagger and interactive documentation APIs")
                        .description("APIs RESTful documentation interactive")
                        .version("v1.0.4")
                );
    }
}
