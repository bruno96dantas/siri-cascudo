package com.nogueira.krusty.krab.krustykrab.model;

import lombok.Getter;

public enum Ingrediente {

    HAMBURGER(1.0, "HAMBURGER"),
    ALFACE(1.0, "ALFACE"),
    BACON(1.0, "BACON"),
    OVO(1.0, "OVO"),
    QUEIJO(1.0, "QUEIJO");

    @Getter
    private Double price;
    @Getter
    private String name;

    Ingrediente(Double price, String name) {
        this.price = price;
        this.name = name;
    }

}
