package com.globalfinanceiro.digimon_api.service;

import com.globalfinanceiro.digimon_api.dto.DigimonDTO;
import com.globalfinanceiro.digimon_api.model.Digimon;
import com.globalfinanceiro.digimon_api.repository.DigimonRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe de serviço para gerenciar Digimons.
 */
@Service
public class DigimonService {

    private final DigimonRepository digimonRepository;

    /**
     * Constrói um novo DigimonService com o DigimonRepository fornecido.
     *
     * @param digimonRepository o repositório de Digimon
     */
    public DigimonService(DigimonRepository digimonRepository) {
        this.digimonRepository = digimonRepository;
    }

    /**
     * Recupera todos os Digimons.
     *
     * @return uma lista de todos os Digimons
     */
    public List<Digimon> getAllDigimons() {
        try {
            List<Digimon> digimons = digimonRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            System.out.println("Operação de busca de todos os Digimons realizada com sucesso.");
            return digimons;
        } catch (Exception e) {
            System.err.println("Erro ao obter todos os Digimons: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Recupera um Digimon pelo seu ID.
     *
     * @param id o ID do Digimon
     * @return um Optional contendo o Digimon se encontrado
     */
    public Optional<Digimon> getDigimonById(Long id) {
        try {
            Optional<Digimon> digimon = digimonRepository.findById(id);
            System.out.println("Operação de busca do Digimon através do ID realizada.");
            return digimon;
        } catch (Exception e) {
            System.err.println("Erro ao obter Digimon por ID: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Recupera Digimons pelo seu nome.
     *
     * @param name o nome a ser pesquisado
     * @return uma lista de Digimons correspondentes
     */
    public List<Digimon> getDigimonByName(String name) {
        try {
            List<Digimon> digimons = digimonRepository.findByNameContaining(name, Sort.by(Sort.Direction.ASC, "id"));
            System.out.println("Operação de busca de Digimons por nome realizada com sucesso.");
            return digimons;
        } catch (Exception e) {
            System.err.println("Erro ao obter Digimon por nome: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Recupera Digimons pelo seu nível.
     *
     * @param level o nível a ser pesquisado
     * @return uma lista de Digimons correspondentes
     */
    public List<Digimon> getDigimonByLevel(String level) {
        try {
            List<Digimon> digimons = digimonRepository.findByLevelContaining(level, Sort.by(Sort.Direction.ASC, "id"));
            System.out.println("Operação de busca de Digimons por nível realizada com sucesso.");
            return digimons;
        } catch (Exception e) {
            System.err.println("Erro ao obter Digimon por nível: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Salva um novo Digimon no banco de dados.
     *
     * @param digimonDto o objeto de transferência de dados do Digimon
     * @return o Digimon salvo
     */
    public Digimon saveDigimon(DigimonDTO digimonDto) {
        try {
            Digimon digimon = new Digimon();
            digimon.setName(digimonDto.getName());
            digimon.setImg(digimonDto.getImg());
            digimon.setLevel(digimonDto.getLevel());
            Digimon savedDigimon = digimonRepository.save(digimon);
            System.out.println("Um novo Digimon foi criado com sucesso.");
            return savedDigimon;
        } catch (Exception e) {
            System.err.println("Erro ao salvar Digimon: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Atualiza um Digimon existente.
     *
     * @param id         o ID do Digimon a ser atualizado
     * @param digimonDto os dados atualizados do Digimon
     * @return um Optional contendo o Digimon atualizado se encontrado
     */
    public Optional<Digimon> updateDigimon(Long id, DigimonDTO digimonDto) {
        try {
            return digimonRepository.findById(id).map(digimon -> {
                digimon.setName(digimonDto.getName());
                digimon.setImg(digimonDto.getImg());
                digimon.setLevel(digimonDto.getLevel());
                Digimon updatedDigimon = digimonRepository.save(digimon);
                System.out.println("Digimon atualizado atualizado com sucesso.");
                return updatedDigimon;
            });
        } catch (Exception e) {
            System.err.println("Erro ao atualizar Digimon: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Deleta um Digimon pelo seu ID.
     *
     * @param id o ID do Digimon a ser deletado
     */
    public void deleteDigimon(Long id) {
        try {
            Optional<Digimon> digimon = digimonRepository.findById(id);
            if (digimon.isPresent()) {
                digimonRepository.deleteById(id);
                System.out.println("Digimon deletado com sucesso.");
            } else {
                System.out.println("Digimon não foi encontrado encontrado para deleção.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao deletar Digimon: " + e.getMessage());
            throw e;
        }
    }
}
