/* CLASSE CONTROLLER */
// DigimonController é a classe que contém os métodos que serão chamados nas rotas da API.
// Ela é responsável por fazer a comunicação entre o cliente e o servidor, chamando os métodos do DigimonService e retornando as respostas adequadas.
package com.globalfinanceiro.digimon_api.controller;

import com.globalfinanceiro.digimon_api.model.Digimon;
import com.globalfinanceiro.digimon_api.service.DigimonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Anotação que indica que a classe é um controller REST.
@RequestMapping("/digimons") // Anotação que indica o caminho base para as rotas.
public class DigimonController {

    @Autowired // Anotação que injeta uma instância de DigimonService.
    private DigimonService digimonService;

    // Rota de busca de todos Digimons
    @GetMapping
    public List<Digimon> getAllDigimons() {
        return digimonService.getAllDigimons();
    }

    // Rota de busca de Digimons pelo ID
    @GetMapping("/id/{id}")
    public ResponseEntity<Digimon> getDigimonById(@PathVariable Long id) {
        Optional<Digimon> digimon = digimonService.getDigimonById(id);
        return digimon.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Rota de busca de Digimons pelo nome
    @GetMapping("/name/{name}")
    public List<Digimon> getDigimonByName(@PathVariable String name) {
        return digimonService.getDigimonByName(name);
    }

    // Rota de busca de Digimons pelo nível
    @GetMapping("/level/{level}")
    public List<Digimon> getDigimonByLevel(@PathVariable String level) {
        return digimonService.getDigimonByLevel(level);
    }

    // Rota de adição de Digimons
    @PostMapping
    public Digimon createDigimon(@RequestBody Digimon digimon) {
        return digimonService.saveDigimon(digimon);
    }

    // Rota de atualização de Digimons pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<Digimon> updateDigimon(@PathVariable Long id, @RequestBody Digimon digimon) {
        Optional<Digimon> updatedDigimon = digimonService.updateDigimon(id, digimon);
        return updatedDigimon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Rota de deleção de Digimons pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDigimon(@PathVariable Long id) {
        digimonService.deleteDigimon(id);
        return ResponseEntity.noContent().build();
    }
}
