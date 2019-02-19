package com.nogueira.krusty.krab.unit.promotion;

import com.nogueira.krusty.krab.model.Ingrediente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Builder
@Data
public class IngredienteContext {

    private Map<Ingrediente, Integer> context;

    private BigDecimal totalPrice;

    public Optional<Map.Entry<Ingrediente, Integer>> getEntry(Ingrediente ingrediente) {
        return context.entrySet().stream()
                .filter(entry -> entry.getKey().equals(ingrediente))
                .findFirst();
    }
}
