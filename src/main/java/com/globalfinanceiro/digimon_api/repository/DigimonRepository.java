package com.globalfinanceiro.digimon_api.repository;

import com.globalfinanceiro.digimon_api.model.Digimon;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Digimon entities.
 */
@Repository
public interface DigimonRepository extends JpaRepository<Digimon, Long> {

    /**
     * Finds Digimons containing the given name.
     *
     * @param name the name to search for
     * @param sort the sort order
     * @return a list of matching Digimons
     */
    List<Digimon> findByNameContaining(String name, Sort sort);

    /**
     * Finds Digimons containing the given level.
     *
     * @param level the level to search for
     * @param sort  the sort order
     * @return a list of matching Digimons
     */
    List<Digimon> findByLevelContaining(String level, Sort sort);
}
