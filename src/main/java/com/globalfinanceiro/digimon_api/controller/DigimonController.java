package com.globalfinanceiro.digimon_api.controller;

import com.globalfinanceiro.digimon_api.model.Digimon;
import com.globalfinanceiro.digimon_api.service.DigimonService;
import com.globalfinanceiro.digimon_api.dto.DigimonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/digimons")
public class DigimonController {

    @Autowired
    private DigimonService digimonService;

    public DigimonController(DigimonService digimonService) {
        this.digimonService = digimonService;
    }

    @GetMapping
    @Operation(summary = "Get all Digimons", description = "Get all Digimons from the database.", tags = "GET")
    public List<Digimon> getAllDigimons() {
        return digimonService.getAllDigimons();
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Get Digimon by ID", description = "Get a Digimon by its ID from the database.", tags = "GET")
    public ResponseEntity<Digimon> getDigimonById(@PathVariable Long id) {
        Optional<Digimon> digimon = digimonService.getDigimonById(id);
        return digimon.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get Digimon by name", description = "Get a Digimon by its name from the database.", tags = "GET")
    public List<Digimon> getDigimonByName(@PathVariable String name) {
        return digimonService.getDigimonByName(name);
    }

    @GetMapping("/level/{level}")
    @Operation(summary = "Get Digimon by level", description = "Get a Digimon by its level from the database.", tags = "GET")
    public List<Digimon> getDigimonByLevel(@PathVariable String level) {
        return digimonService.getDigimonByLevel(level);
    }

    @PostMapping
    @Operation(summary = "Create a Digimon", description = "Create a new Digimon in the database.", tags = "POST")
    public Digimon createDigimon(@RequestBody DigimonDTO digimonDto) {
        return digimonService.saveDigimon(digimonDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Digimon", description = "Update a Digimon by its ID in the database.", tags = "PUT")
    public ResponseEntity<Digimon> updateDigimon(@PathVariable Long id, @RequestBody DigimonDTO digimonDto) {
        Optional<Digimon> updatedDigimon = digimonService.updateDigimon(id, digimonDto);
        return updatedDigimon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Digimon", description = "Delete a Digimon by its ID from the database.", tags = "DELETE")
    public ResponseEntity<Void> deleteDigimon(@PathVariable Long id) {
        digimonService.deleteDigimon(id);
        return ResponseEntity.noContent().build();
    }
}
