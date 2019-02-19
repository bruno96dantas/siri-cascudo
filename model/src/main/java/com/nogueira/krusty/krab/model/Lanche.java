package com.nogueira.krusty.krab.model;

import com.nogueira.krusty.krab.model.utils.IngredienteUtils;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class Lanche {

    private String name;
    private List<Ingrediente> ingredientes;
    private BigDecimal price;

    @Builder
    public Lanche(String name, List<Ingrediente> ingredientes) {
        this.name = name;
        this.ingredientes = ingredientes;
        this.price = getTotalPrice();
    }

    public BigDecimal getTotalPrice() {
        return IngredienteUtils.getTotalPrice(getIngredientes());
    }

    @Override
    public String toString() {
        return "Lanche{" +
                "name='" + name + '\'' +
                ", ingredientes=" + ingredientes +
                '}';
    }
}