package com.globalfinanceiro.digimon_api.dto;

import lombok.Data;

public class DigimonDTO {
    private String name;
    private String img;
    private String level;

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

