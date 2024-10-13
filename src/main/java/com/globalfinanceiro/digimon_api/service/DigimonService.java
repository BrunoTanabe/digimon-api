package com.globalfinanceiro.digimon_api.service;

import com.globalfinanceiro.digimon_api.dto.DigimonDTO;
import com.globalfinanceiro.digimon_api.model.Digimon;
import com.globalfinanceiro.digimon_api.repository.DigimonRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Digimons.
 */
@Service
public class DigimonService {

    private final DigimonRepository digimonRepository;

    /**
     * Constructs a new DigimonService with the given DigimonRepository.
     *
     * @param digimonRepository the Digimon repository
     */
    public DigimonService(DigimonRepository digimonRepository) {
        this.digimonRepository = digimonRepository;
    }

    /**
     * Retrieves all Digimons.
     *
     * @return a list of all Digimons
     */
    public List<Digimon> getAllDigimons() {
        return digimonRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    /**
     * Retrieves a Digimon by its ID.
     *
     * @param id the ID of the Digimon
     * @return an Optional containing the Digimon if found
     */
    public Optional<Digimon> getDigimonById(Long id) {
        return digimonRepository.findById(id);
    }

    /**
     * Retrieves Digimons by their name.
     *
     * @param name the name to search for
     * @return a list of matching Digimons
     */
    public List<Digimon> getDigimonByName(String name) {
        return digimonRepository.findByNameContaining(name, Sort.by(Sort.Direction.ASC, "id"));
    }

    /**
     * Retrieves Digimons by their level.
     *
     * @param level the level to search for
     * @return a list of matching Digimons
     */
    public List<Digimon> getDigimonByLevel(String level) {
        return digimonRepository.findByLevelContaining(level, Sort.by(Sort.Direction.ASC, "id"));
    }

    /**
     * Saves a new Digimon to the database.
     *
     * @param digimonDto the Digimon data transfer object
     * @return the saved Digimon
     */
    public Digimon saveDigimon(DigimonDTO digimonDto) {
        Digimon digimon = new Digimon();
        digimon.setName(digimonDto.getName());
        digimon.setImg(digimonDto.getImg());
        digimon.setLevel(digimonDto.getLevel());
        return digimonRepository.save(digimon);
    }

    /**
     * Updates an existing Digimon.
     *
     * @param id         the ID of the Digimon to update
     * @param digimonDto the updated Digimon data
     * @return an Optional containing the updated Digimon if found
     */
    public Optional<Digimon> updateDigimon(Long id, DigimonDTO digimonDto) {
        return digimonRepository.findById(id).map(digimon -> {
            digimon.setName(digimonDto.getName());
            digimon.setImg(digimonDto.getImg());
            digimon.setLevel(digimonDto.getLevel());
            return digimonRepository.save(digimon);
        });
    }

    /**
     * Deletes a Digimon by its ID.
     *
     * @param id the ID of the Digimon to delete
     */
    public void deleteDigimon(Long id) {
        digimonRepository.deleteById(id);
    }
}
