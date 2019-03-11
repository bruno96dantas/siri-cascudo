package com.nogueira.krusty.krab.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Pedido {

    private List<Lanche> lanches;
    private BigDecimal price;

    @Builder
    public Pedido(List<Lanche> lanches, BigDecimal price) {
        this.lanches = lanches;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "lanches=" + lanches +
                ", price=" + price +
                '}';
    }
}
