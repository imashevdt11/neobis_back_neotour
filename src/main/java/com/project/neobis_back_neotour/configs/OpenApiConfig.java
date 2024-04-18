package com.project.neobis_back_neotour.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Diyas",
                        email = "imashevdt@gmail.com"
                ),
                description = "Open API documentation for NeoTour app",
                title = "NeoTour"
        )
)
public class OpenApiConfig {}