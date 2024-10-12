package com.globalfinanceiro.digimon_api.service;

import com.globalfinanceiro.digimon_api.model.Digimon;
import com.globalfinanceiro.digimon_api.repository.DigimonRepository;
import com.globalfinanceiro.digimon_api.dto.DigimonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DigimonService {

    @Autowired
    private DigimonRepository digimonRepository;

    public List<Digimon> getAllDigimons() {
        return digimonRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Optional<Digimon> getDigimonById(Long id) {
        return digimonRepository.findById(id);
    }

    public List<Digimon> getDigimonByName(String name) {
        return digimonRepository.findByNameContaining(name, Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<Digimon> getDigimonByLevel(String level) {
        return digimonRepository.findByLevelContaining(level, Sort.by(Sort.Direction.ASC, "id"));
    }

    public Digimon saveDigimon(DigimonDTO digimonCreationDTO) {
        Digimon digimon = new Digimon();
        digimon.setName(digimonCreationDTO.getName());
        digimon.setImg(digimonCreationDTO.getImg());
        digimon.setLevel(digimonCreationDTO.getLevel());
        return digimonRepository.save(digimon);
    }

    public Optional<Digimon> updateDigimon(Long id, DigimonDTO digimonUpdateDTO) {
        return digimonRepository.findById(id).map(digimon -> {
            digimon.setName(digimonUpdateDTO.getName());
            digimon.setImg(digimonUpdateDTO.getImg());
            digimon.setLevel(digimonUpdateDTO.getLevel());
            return digimonRepository.save(digimon);
        });
    }

    public void deleteDigimon(Long id) {
        digimonRepository.deleteById(id);
    }
}