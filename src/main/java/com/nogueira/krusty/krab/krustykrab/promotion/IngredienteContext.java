package com.nogueira.krusty.krab.krustykrab.promotion;

import com.nogueira.krusty.krab.krustykrab.model.Ingrediente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Builder
@Data
public class IngredienteContext {

    private Map<Ingrediente, Integer> context;

    public Optional<Map.Entry<Ingrediente, Integer>> getEntryByName(String ingredienteName) {
        return context.entrySet().stream()
                .filter(entry -> entry.getKey().getName().equalsIgnoreCase(ingredienteName))
                .findFirst();
    }
}
