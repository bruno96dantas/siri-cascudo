package com.nogueira.krusty.krab.krustykrab.rules;

import com.nogueira.krusty.krab.krustykrab.model.Ingrediente;
import com.nogueira.krusty.krab.krustykrab.promotion.IngredientesContext;
import lombok.Builder;

public class RuleLight extends RuleMain {

    private final String BACON_NAME = "BACON";
    private final Double PERCENTAGE_OF_DISCOUNT = 0.1;

    @Builder
    public RuleLight() {
        super("ALFACE");
    }

    @Override
    public Double getDiscount(IngredientesContext context, Ingrediente ingrediente, Double totalPrice) {

        Integer qtyAlface = context.getContext().getOrDefault(getTargetIngredienteName(), 0);
        Integer qtyBacon = context.getContext().getOrDefault(BACON_NAME, 0);

        if ((qtyAlface > 0) && qtyBacon == 0) {
            return totalPrice * PERCENTAGE_OF_DISCOUNT;
        }

        return 0.0;
    }


}
