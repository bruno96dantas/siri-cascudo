package com.nogueira.krusty.krab.krustykrab.model;

import com.nogueira.krusty.krab.krustykrab.Utils.IngredienteUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lanche {

    private String name;

    private List<Ingrediente> ingredientes;

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
