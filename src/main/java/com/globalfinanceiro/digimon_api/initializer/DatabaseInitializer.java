package com.globalfinanceiro.digimon_api.initializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globalfinanceiro.digimon_api.model.Digimon;
import com.globalfinanceiro.digimon_api.repository.DigimonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Inicializa o banco de dados ao iniciar a aplicação.
 * Verifica se a tabela 'digimon' existe e carrega dados iniciais do arquivo JSON, se necessário.
 */
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final DigimonRepository digimonRepository;

    /**
     * Constrói um novo DatabaseInitializer com o JdbcTemplate e o DigimonRepository fornecidos.
     *
     * @param jdbcTemplate      o JdbcTemplate para operações no banco de dados
     * @param digimonRepository o repositório de Digimon
     */
    public DatabaseInitializer(JdbcTemplate jdbcTemplate, DigimonRepository digimonRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.digimonRepository = digimonRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        String message = AnsiOutput.toString(AnsiColor.BRIGHT_YELLOW, "Verificando se a tabela 'digimon' já existe", AnsiColor.DEFAULT);
        System.out.println(message);
        boolean tableExists = checkIfTableExists();

        if (!tableExists) {
            message = AnsiOutput.toString(AnsiColor.BRIGHT_YELLOW, "A tabela ainda não existe", AnsiColor.DEFAULT);
            System.out.println(message);
            createTable();
            message = AnsiOutput.toString(AnsiColor.BRIGHT_GREEN, AnsiStyle.BOLD, "Tabela criada com sucesso!", AnsiColor.DEFAULT, AnsiStyle.NORMAL);
            System.out.println(message);
            loadDataFromJson();
            message = AnsiOutput.toString(AnsiColor.BRIGHT_GREEN, AnsiStyle.BOLD, "Dados de Digimon carregados com sucesso", AnsiColor.DEFAULT, AnsiStyle.NORMAL);
            System.out.println(message);
        } else {
            message = AnsiOutput.toString(AnsiColor.BRIGHT_YELLOW, "Tabela já existe", AnsiColor.DEFAULT);
            System.out.println(message);
            message = AnsiOutput.toString(AnsiColor.BRIGHT_GREEN, AnsiStyle.BOLD, "Dados de Digimon já estão carregados", AnsiColor.DEFAULT, AnsiStyle.NORMAL);
            System.out.println(message);
        }
    }

    /**
     * Verifica se a tabela 'digimon' existe no banco de dados.
     *
     * @return true se a tabela existe, false caso contrário
     */
    private boolean checkIfTableExists() {
        try {
            String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'digimon'";
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
            return count != null && count > 0;
        } catch (Exception e) {
            System.err.println("Erro ao verificar se a tabela existe: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Cria a tabela 'digimon' no banco de dados.
     */
    private void createTable() {
        try {
            String createTableSql = "CREATE TABLE digimon (" +
                    "id SERIAL PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "img VARCHAR(255) NOT NULL," +
                    "level VARCHAR(255) NOT NULL)";
            jdbcTemplate.execute(createTableSql);
            System.out.println("Tabela 'digimon' criada com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao criar a tabela 'digimon': " + e.getMessage());
            throw e;
        }
    }

    /**
     * Carrega dados iniciais de Digimon do arquivo JSON para o banco de dados.
     */
    private void loadDataFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = new ClassPathResource("digimons_data.json").getInputStream()) {
            List<Digimon> digimons = objectMapper.readValue(inputStream, new TypeReference<List<Digimon>>() {});
            digimonRepository.saveAll(digimons);
            System.out.println("Dados de Digimons carregados e inseridos com sucesso.");
        } catch (IOException e) {
            String errorMessage = AnsiOutput.toString(AnsiColor.RED, "Erro ao carregar dados do arquivo JSON: " + e.getMessage(), AnsiColor.DEFAULT);
            System.err.println(errorMessage);
            throw new RuntimeException(e);
        }
    }
}
