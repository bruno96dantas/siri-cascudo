package com.nogueira.krusty.krab.krustykrab.rules;

import com.nogueira.krusty.krab.krustykrab.model.Ingrediente;
import com.nogueira.krusty.krab.krustykrab.promotion.IngredientesContext;

public abstract class RuleMuito extends RuleMain {

    private final int MINIMUM_PORTION = 3;

    public static void main(String[] args) {
        int i = 2 / 3;
    }

    public RuleMuito(String targetIngredienteName) {
        super(targetIngredienteName);
    }

    @Override
    public Double getDiscount(IngredientesContext context, Ingrediente ingrediente, Double totalPrice) {

        Integer targetIngredientQty = context.getContext().getOrDefault(getTargetIngredienteName(), 0);

        if (targetIngredientQty == 0) {
            return 0.0;
        }

        int i = targetIngredientQty / MINIMUM_PORTION;

        if (i == 0) {
            return 0.0;
        }

        return i * ingrediente.getPrice();
    }

}
