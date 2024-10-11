/* CLASSE PRINCIPAL DA APLICAÇÃO */
// Classe inicial da aplicação, responsável por iniciar a aplicação Spring Boot e executar o projeto.
package com.globalfinanceiro.digimon_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Anotação que indica que a classe é uma classe de configuração e inicialização do Spring Boot.
public class DigimonApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigimonApiApplication.class, args);
	}

}
