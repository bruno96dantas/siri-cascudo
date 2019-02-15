package com.nogueira.krusty.krab.krustykrab.promotion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
@Builder
@Data
public class IngredientesContext {
    private Map<String, Integer> context;
}
