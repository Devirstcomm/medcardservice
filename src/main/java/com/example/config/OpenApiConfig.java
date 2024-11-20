package com.example.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Dictionary of Diseases System Api",
                description = "API системы справочников заболеваний",
                version = "1.0.0",
                contact = @Contact(
                        name = "Medvedev Artyom",
                        email = "artyom.medvedev.dev@gmail.com"
                )
        )
)
public class OpenApiConfig {
    // Конфигурация для Swagger
}
