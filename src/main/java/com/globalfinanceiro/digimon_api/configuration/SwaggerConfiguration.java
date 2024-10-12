package com.globalfinanceiro.digimon_api.configuration;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@Configuration
@OpenAPIDefinition(info = @Info(title = "Digimon API", description = "API for querying Digimon for the GF Software Challenge!", version = "0.1", contact = @io.swagger.v3.oas.annotations.info.Contact(name = "Bruno Tanabe", email = "tanabebruno@gmail.com", url = "https://www.linkedin.com/in/tanabebruno/"), license = @io.swagger.v3.oas.annotations.info.License(name = "MIT License", url = "https://github.com/BrunoTanabe/digimon-api/blob/main/LICENSE")))
public class SwaggerConfiguration {
}
