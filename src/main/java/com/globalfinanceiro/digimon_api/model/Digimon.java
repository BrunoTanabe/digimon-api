package com.globalfinanceiro.digimon_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "digimon")
public class Digimon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String img;
    private String level;

    public Digimon() {}

    /**
     * Constructs a new Digimon with the given attributes.
     *
     * @param name  the name of the Digimon
     * @param img   the image URL of the Digimon
     * @param level the level of the Digimon
     */
    public Digimon(String name, String img, String level) {
        this.name = name;
        this.img = img;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
