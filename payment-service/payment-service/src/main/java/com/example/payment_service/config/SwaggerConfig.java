package com.example.payment_service.config;

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
                        .title("APIs test payment with swagger")
                        .description(".APIs REST of payment test with swagger and APIs interactive documentation")
                        .version("v1.0.4")
                );
    }
}
