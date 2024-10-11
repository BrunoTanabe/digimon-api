/* CLASSE SERVICE */
// A classe DigimonService é responsável por implementar a lógica de negócios da aplicação.
// Ela é responsável por realizar a comunicação entre o controlador e o repositório. Ela contém métodos que realizam operações CRUD (criar, ler, atualizar e excluir) no banco de dados.
package com.globalfinanceiro.digimon_api.service;

import com.globalfinanceiro.digimon_api.model.Digimon;
import com.globalfinanceiro.digimon_api.repository.DigimonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Anotação que indica que a classe é um serviço.
public class DigimonService {

    @Autowired // Anotação que injeta uma instância de DigimonRepository.
    private DigimonRepository digimonRepository;

    // Retorna todos os Digimons
    public List<Digimon> getAllDigimons() {
        return digimonRepository.findAll();
    }

    // Retorna Digimons pelo ID
    public Optional<Digimon> getDigimonById(Long id) {
        return digimonRepository.findById(id);
    }

    // Retorna Digimons pelo nome
    public List<Digimon> getDigimonByName(String name) {
        return digimonRepository.findByName(name);
    }

    // Retorna Digimons pelo nível
    public List<Digimon> getDigimonByLevel(String level) {
        return digimonRepository.findByLevel(level);
    }

    // Adiciona Digimons
    public Digimon saveDigimon(Digimon digimon) {
        return digimonRepository.save(digimon);
    }

    // Atualiza Digimons pelo ID
    public Optional<Digimon> updateDigimon(Long id, Digimon updatedDigimon) {
        return digimonRepository.findById(id).map(digimon -> {
            digimon.setName(updatedDigimon.getName());
            digimon.setImg(updatedDigimon.getImg());
            digimon.setLevel(updatedDigimon.getLevel());
            return digimonRepository.save(digimon);
        });
    }

    // Deleta Digimons pelo ID
    public void deleteDigimon(Long id) {
        digimonRepository.deleteById(id);
    }
}



