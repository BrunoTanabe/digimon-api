package com.globalfinanceiro.digimon_api.repository;

import com.globalfinanceiro.digimon_api.model.Digimon;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface de repositório para entidades Digimon.
 */
@Repository
public interface DigimonRepository extends JpaRepository<Digimon, Long> {

    /**
     * Encontra Digimons que contêm o nome fornecido.
     *
     * @param name o nome a ser pesquisado
     * @param sort a ordem de classificação
     * @return uma lista de Digimons correspondentes
     */
    List<Digimon> findByNameContaining(String name, Sort sort);

    /**
     * Encontra Digimons que contêm o nível fornecido.
     *
     * @param level o nível a ser pesquisado
     * @param sort  a ordem de classificação
     * @return uma lista de Digimons correspondentes
     */
    List<Digimon> findByLevelContaining(String level, Sort sort);
}
