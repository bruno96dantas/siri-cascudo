package com.nogueira.krusty.krab.krustykrab.rules;

import com.nogueira.krusty.krab.krustykrab.model.Ingrediente;
import com.nogueira.krusty.krab.krustykrab.promotion.IngredienteContext;

public abstract class RuleMuito extends RuleMain {

    private final int MINIMUM_PORTION = 3;

    public RuleMuito(String targetIngredienteName) {
        super(targetIngredienteName);
    }

    @Override
    public Double getDiscount(IngredienteContext context, Double totalPrice) {
        /* find entity name in context*/
        return context.getEntryByName(getTargetIngredienteName())
                .map(entry -> {
                    Integer targetIngredientQty = entry.getValue();
                    Ingrediente ingrediente = entry.getKey();
                    int i = targetIngredientQty / MINIMUM_PORTION;
                    return i * ingrediente.getPrice();
                })
                .orElse(0.0);
    }

}
