package com.loctran.service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Perfume Service Documentation",
        version = "1.0",
        description = "API documentation for the Perfume Service"
    ),
    servers = {
        @Server(url = "http://localhost:8090", description = "Development Server")
    }
)
public class SwaggerConfig {

}
