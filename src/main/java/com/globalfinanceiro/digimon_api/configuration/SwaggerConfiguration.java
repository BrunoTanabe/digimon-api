package com.globalfinanceiro.digimon_api.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Swagger configuration class for OpenAPI documentation.
 * Sets up basic metadata for the API documentation.
 */
@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Digimon API",
        version = "0.1",
        description = "API for querying Digimon for the GF Software Challenge!",
        contact = @io.swagger.v3.oas.annotations.info.Contact(
                name = "Bruno Tanabe",
                email = "tanabebruno@gmail.com",
                url = "https://www.linkedin.com/in/tanabebruno/"
        ),
        license = @io.swagger.v3.oas.annotations.info.License(
                name = "MIT License",
                url = "https://github.com/BrunoTanabe/digimon-api/blob/main/LICENSE"
        )
))
public class SwaggerConfiguration {
    // No implementation needed; configuration is handled via annotations.
}
