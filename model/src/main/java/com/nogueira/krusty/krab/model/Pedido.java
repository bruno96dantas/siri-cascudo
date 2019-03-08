package com.nogueira.krusty.krab.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Pedido {

    private List<Lanche> lanches;
    private BigDecimal price;

    @Builder
    public Pedido(List<Lanche> lanches) {
        this.lanches = lanches;
        this.price = getTotalPrice();
    }

    public BigDecimal getTotalPrice() {
        return lanches.stream()
                .map(Lanche::getPrice)
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal("0.0"));
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "lanches=" + lanches +
                ", price=" + price +
                '}';
    }
}
