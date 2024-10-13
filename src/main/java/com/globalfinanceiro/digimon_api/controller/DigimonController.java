package com.globalfinanceiro.digimon_api.controller;

import com.globalfinanceiro.digimon_api.dto.DigimonDTO;
import com.globalfinanceiro.digimon_api.model.Digimon;
import com.globalfinanceiro.digimon_api.service.DigimonService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Digimons.
 */
@RestController
@RequestMapping("/digimons")
public class DigimonController {

    private final DigimonService digimonService;

    /**
     * Constructs a new DigimonController with the given DigimonService.
     *
     * @param digimonService the Digimon service
     */
    public DigimonController(DigimonService digimonService) {
        this.digimonService = digimonService;
    }

    /**
     * Retrieves all Digimons.
     *
     * @return a list of all Digimons
     */
    @GetMapping
    @Operation(summary = "Get all Digimons", description = "Retrieve all Digimons from the database.", tags = "GET")
    public List<Digimon> getAllDigimons() {
        return digimonService.getAllDigimons();
    }

    /**
     * Retrieves a Digimon by its ID.
     *
     * @param id the ID of the Digimon
     * @return the Digimon if found, or 404 Not Found
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "Get Digimon by ID", description = "Retrieve a Digimon by its ID from the database.", tags = "GET")
    public ResponseEntity<Digimon> getDigimonById(@PathVariable Long id) {
        return digimonService.getDigimonById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves Digimons by their name.
     *
     * @param name the name of the Digimon(s)
     * @return a list of matching Digimons
     */
    @GetMapping("/name/{name}")
    @Operation(summary = "Get Digimon by name", description = "Retrieve Digimons by their name from the database.", tags = "GET")
    public List<Digimon> getDigimonByName(@PathVariable String name) {
        return digimonService.getDigimonByName(name);
    }

    /**
     * Retrieves Digimons by their level.
     *
     * @param level the level of the Digimon(s)
     * @return a list of matching Digimons
     */
    @GetMapping("/level/{level}")
    @Operation(summary = "Get Digimon by level", description = "Retrieve Digimons by their level from the database.", tags = "GET")
    public List<Digimon> getDigimonByLevel(@PathVariable String level) {
        return digimonService.getDigimonByLevel(level);
    }

    /**
     * Creates a new Digimon.
     *
     * @param digimonDto the Digimon data transfer object
     * @return the created Digimon
     */
    @PostMapping
    @Operation(summary = "Create a Digimon", description = "Create a new Digimon in the database.", tags = "POST")
    public Digimon createDigimon(@RequestBody DigimonDTO digimonDto) {
        return digimonService.saveDigimon(digimonDto);
    }

    /**
     * Updates an existing Digimon by ID.
     *
     * @param id         the ID of the Digimon to update
     * @param digimonDto the updated Digimon data
     * @return the updated Digimon if found, or 404 Not Found
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a Digimon", description = "Update a Digimon by its ID in the database.", tags = "PUT")
    public ResponseEntity<Digimon> updateDigimon(@PathVariable Long id, @RequestBody DigimonDTO digimonDto) {
        Optional<Digimon> updatedDigimon = digimonService.updateDigimon(id, digimonDto);
        return updatedDigimon.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Deletes a Digimon by ID.
     *
     * @param id the ID of the Digimon to delete
     * @return 204 No Content if deletion was successful
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Digimon", description = "Delete a Digimon by its ID from the database.", tags = "DELETE")
    public ResponseEntity<Void> deleteDigimon(@PathVariable Long id) {
        digimonService.deleteDigimon(id);
        return ResponseEntity.noContent().build();
    }
}
