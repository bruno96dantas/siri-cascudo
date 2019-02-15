package com.nogueira.krusty.krab.krustykrab.rules;

import com.nogueira.krusty.krab.krustykrab.model.Ingrediente;
import com.nogueira.krusty.krab.krustykrab.promotion.IngredientesContext;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class RuleMain {

    private String targetIngredienteName;

    public abstract Double getDiscount(IngredientesContext context, Ingrediente ingrediente, Double totalPrice);

    public boolean isTargetIngredient(String currentIngredientName) {

        return getTargetIngredienteName().equalsIgnoreCase(currentIngredientName);

    }
}
