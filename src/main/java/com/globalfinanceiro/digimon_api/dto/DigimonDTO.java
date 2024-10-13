package com.globalfinanceiro.digimon_api.dto;

// import lombok.Data;

/**
 * Data Transfer Object for Digimon.
 */
// @Data
public class DigimonDTO {
    private String name;
    private String img;
    private String level;

    // Lombok @Data generates getters, setters, equals, hashCode, and toString methods, but docker was not working with '@data' annotation
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


