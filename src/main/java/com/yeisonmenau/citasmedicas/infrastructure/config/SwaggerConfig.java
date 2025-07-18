package com.yeisonmenau.citasmedicas.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Citas Médicas")
                        .description("Sistema de gestión de citas médicas que permite administrar pacientes, médicos y sus citas")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Yeison Menau")
                                .url("https://github.com/yeisonmenau")));
    }
}