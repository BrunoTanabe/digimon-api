package com.globalfinanceiro.digimon_api.repository;

import com.globalfinanceiro.digimon_api.model.Digimon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DigimonRepository extends JpaRepository<Digimon, Long> {

    List<Digimon> findByName(String name);
    List<Digimon> findByLevel(String level);
}

