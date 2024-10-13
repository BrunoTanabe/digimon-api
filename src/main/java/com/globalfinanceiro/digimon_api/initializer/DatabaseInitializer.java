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
 * Initializes the database on application startup.
 * Checks if the 'digimon' table exists and loads initial data from JSON file if needed.
 */
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final DigimonRepository digimonRepository;

    /**
     * Constructs a new DatabaseInitializer with the given JdbcTemplate and DigimonRepository.
     *
     * @param jdbcTemplate      the JdbcTemplate for database operations
     * @param digimonRepository the Digimon repository
     */
    public DatabaseInitializer(JdbcTemplate jdbcTemplate, DigimonRepository digimonRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.digimonRepository = digimonRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        String message = AnsiOutput.toString(AnsiColor.BRIGHT_YELLOW, "Checking if the 'digimon' table already exists", AnsiColor.DEFAULT);
        System.out.println(message);
        boolean tableExists = checkIfTableExists();

        if (!tableExists) {
            message = AnsiOutput.toString(AnsiColor.BRIGHT_YELLOW, "The table does not exist yet", AnsiColor.DEFAULT);
            System.out.println(message);
            createTable();
            message = AnsiOutput.toString(AnsiColor.BRIGHT_GREEN, AnsiStyle.BOLD, "Table created successfully!", AnsiColor.DEFAULT, AnsiStyle.NORMAL);
            System.out.println(message);
            loadDataFromJson();
            message = AnsiOutput.toString(AnsiColor.BRIGHT_GREEN, AnsiStyle.BOLD, "Digimon data loaded successfully", AnsiColor.DEFAULT, AnsiStyle.NORMAL);
            System.out.println(message);
        } else {
            message = AnsiOutput.toString(AnsiColor.BRIGHT_YELLOW, "Table already exists", AnsiColor.DEFAULT);
            System.out.println(message);
            message = AnsiOutput.toString(AnsiColor.BRIGHT_GREEN, AnsiStyle.BOLD, "Digimon data loaded successfully", AnsiColor.DEFAULT, AnsiStyle.NORMAL);
            System.out.println(message);
        }
    }

    /**
     * Checks if the 'digimon' table exists in the database.
     *
     * @return true if the table exists, false otherwise
     */
    private boolean checkIfTableExists() {
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'digimon'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null && count > 0;
    }

    /**
     * Creates the 'digimon' table in the database.
     */
    private void createTable() {
        String createTableSql = "CREATE TABLE digimon (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "img VARCHAR(255) NOT NULL," +
                "level VARCHAR(255) NOT NULL)";
        jdbcTemplate.execute(createTableSql);
    }

    /**
     * Loads initial Digimon data from JSON file into the database.
     */
    private void loadDataFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = new ClassPathResource("digimons_data.json").getInputStream()) {
            List<Digimon> digimons = objectMapper.readValue(inputStream, new TypeReference<List<Digimon>>() {});
            digimonRepository.saveAll(digimons);
        } catch (IOException e) {
            String errorMessage = AnsiOutput.toString(AnsiColor.RED, "Error loading data from JSON file: " + e.getMessage(), AnsiColor.DEFAULT);
            System.err.println(errorMessage);
        }
    }
}
