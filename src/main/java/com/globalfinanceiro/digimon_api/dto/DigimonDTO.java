package com.globalfinanceiro.digimon_api.dto;

// import lombok.Data;

/**
 * Objeto de Transferência de Dados (DTO) para Digimon.
 */
// @Data
public class DigimonDTO {
    private String name;
    private String img;
    private String level;

    // A anotação @Data do Lombok gera getters, setters, equals, hashCode, and toString methods, mas o Docker não funciona corretamente com a anotação @Data.
    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}


