package com.globalfinanceiro.digimon_api.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Classe de configuração do Swagger para documentação OpenAPI.
 * Configura metadados básicos para a documentação da API.
 */
@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Digimon API",
        version = "0.1",
        description = "API para consultar Digimons para o Desafio GF Software!",
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
        // Nenhuma implementação necessária; a configuração é feita via anotações.
}
