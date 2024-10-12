package com.globalfinanceiro.digimon_api.service;

import com.globalfinanceiro.digimon_api.model.Digimon;
import com.globalfinanceiro.digimon_api.repository.DigimonRepository;
import com.globalfinanceiro.digimon_api.dto.DigimonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Anotação que indica que a classe é um serviço.
public class DigimonService {

    @Autowired // Anotação que injeta uma instância de DigimonRepository.
    private DigimonRepository digimonRepository;

    // Retorna todos os Digimons
    public List<Digimon> getAllDigimons() {
        return digimonRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    // Retorna Digimons pelo ID
    public Optional<Digimon> getDigimonById(Long id) {
        return digimonRepository.findById(id);
    }

    // Retorna Digimons pelo nome
    public List<Digimon> getDigimonByName(String name) {
        return digimonRepository.findByNameContaining(name, Sort.by(Sort.Direction.ASC, "id"));
    }

    // Retorna Digimons pelo nível
    public List<Digimon> getDigimonByLevel(String level) {
        return digimonRepository.findByLevelContaining(level, Sort.by(Sort.Direction.ASC, "id"));
    }

    // Adiciona Digimons
    public Digimon saveDigimon(DigimonDTO digimonCreationDTO) {
        Digimon digimon = new Digimon();
        digimon.setName(digimonCreationDTO.getName());
        digimon.setImg(digimonCreationDTO.getImg());
        digimon.setLevel(digimonCreationDTO.getLevel());
        return digimonRepository.save(digimon);
    }

    // Atualiza Digimons pelo ID
    public Optional<Digimon> updateDigimon(Long id, DigimonDTO digimonUpdateDTO) {
        return digimonRepository.findById(id).map(digimon -> {
            digimon.setName(digimonUpdateDTO.getName());
            digimon.setImg(digimonUpdateDTO.getImg());
            digimon.setLevel(digimonUpdateDTO.getLevel());
            return digimonRepository.save(digimon);
        });
    }

    // Deleta Digimons pelo ID
    public void deleteDigimon(Long id) {
        digimonRepository.deleteById(id);
    }
}