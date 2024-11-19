package br.com.holytickets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springAPI() {
        return new OpenAPI().info(new Info().title("Holytickets - Ticket Sales API")
                        .description("Backend API for ticket sales")
                        .version("v2.0.0"));
    }
}
