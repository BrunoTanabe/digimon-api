package com.globalfinanceiro.digimon_api.repository;

import com.globalfinanceiro.digimon_api.model.Digimon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DigimonRepository extends JpaRepository<Digimon, Long> {
    List<Digimon> findByNameContaining(String name, Sort sort);
    List<Digimon> findByLevelContaining(String level, Sort sort);
}

