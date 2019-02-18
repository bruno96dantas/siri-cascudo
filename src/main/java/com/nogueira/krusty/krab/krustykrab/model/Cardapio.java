package com.nogueira.krusty.krab.krustykrab.model;

import lombok.Getter;

import static com.nogueira.krusty.krab.krustykrab.model.Ingrediente.*;
import static java.util.Arrays.asList;

public enum Cardapio {
    XBACON(Lanche.builder().ingredientes(asList(BACON, HAMBURGER, QUEIJO)).name("X-BACON").build()),
    XBURGER(Lanche.builder().ingredientes(asList(HAMBURGER, QUEIJO)).name("X-BURGER").build()),
    XEGG(Lanche.builder().ingredientes(asList(OVO, HAMBURGER, QUEIJO)).name("X-EGG").build()),
    XEGGBACON(Lanche.builder().ingredientes(asList(OVO, BACON, HAMBURGER, QUEIJO)).name("X-EGG-BACON").build());

    @Getter
    private Lanche lanche;

    Cardapio(Lanche lanche) {
        this.lanche = lanche;
    }
}
