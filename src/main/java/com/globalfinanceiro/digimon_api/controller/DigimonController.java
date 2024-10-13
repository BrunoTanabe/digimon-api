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
 * Controlador REST para gerenciar Digimons.
 */
@RestController
@RequestMapping("/digimons")
public class DigimonController {

    private final DigimonService digimonService;

    /**
     * Constrói um novo DigimonController com o DigimonService fornecido.
     *
     * @param digimonService o serviço de Digimon
     */
    public DigimonController(DigimonService digimonService) {
        this.digimonService = digimonService;
    }

    /**
     * Recupera todos os Digimons.
     *
     * @return uma lista de todos os Digimons
     */
    @GetMapping
    @Operation(summary = "Obter todos os Digimons", description = "Recupera todos os Digimons do banco de dados.", tags = "GET")
    public List<Digimon> getAllDigimons() {
        try {
            return digimonService.getAllDigimons();
        } catch (Exception e) {
            System.err.println("Erro ao obter todos os Digimons: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Recupera um Digimon pelo seu ID.
     *
     * @param id o ID do Digimon
     * @return o Digimon se encontrado, ou 404 Not Found
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "Obter Digimon por ID", description = "Recupera um Digimon pelo seu ID do banco de dados.", tags = "GET")
    public ResponseEntity<Digimon> getDigimonById(@PathVariable Long id) {
        try {
            Optional<Digimon> digimon = digimonService.getDigimonById(id);
            return digimon.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            System.err.println("Erro ao obter Digimon por ID: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * Recupera Digimons pelo seu nome.
     *
     * @param name o nome do(s) Digimon(s)
     * @return uma lista de Digimons correspondentes
     */
    @GetMapping("/name/{name}")
    @Operation(summary = "Obter Digimon por nome", description = "Recupera Digimons pelo seu nome do banco de dados.", tags = "GET")
    public List<Digimon> getDigimonByName(@PathVariable String name) {
        try {
            return digimonService.getDigimonByName(name);
        } catch (Exception e) {
            System.err.println("Erro ao obter Digimon por nome: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Recupera Digimons pelo seu nível.
     *
     * @param level o nível do(s) Digimon(s)
     * @return uma lista de Digimons correspondentes
     */
    @GetMapping("/level/{level}")
    @Operation(summary = "Obter Digimon por nível", description = "Recupera Digimons pelo seu nível do banco de dados.", tags = "GET")
    public List<Digimon> getDigimonByLevel(@PathVariable String level) {
        try {
            return digimonService.getDigimonByLevel(level);
        } catch (Exception e) {
            System.err.println("Erro ao obter Digimon por nível: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Cria um novo Digimon.
     *
     * @param digimonDto o objeto de transferência de dados do Digimon
     * @return o Digimon criado
     */
    @PostMapping
    @Operation(summary = "Criar um Digimon", description = "Cria um novo Digimon no banco de dados.", tags = "POST")
    public ResponseEntity<Digimon> createDigimon(@RequestBody DigimonDTO digimonDto) {
        try {
            Digimon createdDigimon = digimonService.saveDigimon(digimonDto);
            return ResponseEntity.ok(createdDigimon);
        } catch (Exception e) {
            System.err.println("Erro ao criar Digimon: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * Atualiza um Digimon existente por ID.
     *
     * @param id         o ID do Digimon a ser atualizado
     * @param digimonDto os dados atualizados do Digimon
     * @return o Digimon atualizado se encontrado, ou 404 Not Found
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um Digimon", description = "Atualiza um Digimon pelo seu ID no banco de dados.", tags = "PUT")
    public ResponseEntity<Digimon> updateDigimon(@PathVariable Long id, @RequestBody DigimonDTO digimonDto) {
        try {
            Optional<Digimon> updatedDigimon = digimonService.updateDigimon(id, digimonDto);
            return updatedDigimon.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            System.err.println("Erro ao atualizar Digimon: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * Deleta um Digimon pelo ID.
     *
     * @param id o ID do Digimon a ser deletado
     * @return 204 No Content se a deleção foi bem-sucedida
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um Digimon", description = "Deleta um Digimon pelo seu ID do banco de dados.", tags = "DELETE")
    public ResponseEntity<Void> deleteDigimon(@PathVariable Long id) {
        try {
            digimonService.deleteDigimon(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.err.println("Erro ao deletar Digimon: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}
