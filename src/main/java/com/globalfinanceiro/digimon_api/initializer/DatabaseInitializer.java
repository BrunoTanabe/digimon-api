package com.globalfinanceiro.digimon_api.initializer;

import com.globalfinanceiro.digimon_api.model.Digimon;
import com.globalfinanceiro.digimon_api.repository.DigimonRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DigimonRepository digimonRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Verificar se a tabela "digimon" existe
        Boolean tableExists = checkIfTableExists();

        if (!tableExists) {
            // Se a tabela não existir, cria a tabela e insere os dados do JSON
            createTable();
            loadDataFromJson();
        }
    }

    private Boolean checkIfTableExists() {
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'digimon'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        if (count != null && count > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    private void createTable() {
        jdbcTemplate.execute("CREATE TABLE digimon (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "img VARCHAR(255) NOT NULL," +
                "level VARCHAR(255) NOT NULL)");
        System.out.println("Tabela 'digimon' criada com sucesso.");
    }

    private void loadDataFromJson() {
        // Carrega os dados do arquivo JSON e insere no banco de dados
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream inputStream = new ClassPathResource("digimons_data.json").getInputStream();
            List<Digimon> digimons = objectMapper.readValue(inputStream, new TypeReference<List<Digimon>>() {});
            digimonRepository.saveAll(digimons);
            System.out.println("Dados de Digimons carregados e inseridos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados do arquivo JSON: " + e.getMessage());
        }
    }
}

