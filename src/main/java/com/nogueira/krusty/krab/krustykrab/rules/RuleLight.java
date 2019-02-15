package com.nogueira.krusty.krab.krustykrab.rules;

import com.nogueira.krusty.krab.krustykrab.promotion.IngredienteContext;
import lombok.Builder;

import java.util.Map;

public class RuleLight extends RuleMain {

    private final String BACON_NAME = "BACON";
    private final Double PERCENTAGE_OF_DISCOUNT = 0.1;

    @Builder
    public RuleLight() {
        super("ALFACE");
    }

    @Override
    public Double getDiscount(IngredienteContext context, Double totalPrice) {
        /* find bacon in context, if present it has a value */
        Integer baconQty = context.getEntryByName(BACON_NAME)
                .map(Map.Entry::getValue)
                .orElse(0);
        /* find alface in context, if present, it will check baconQty if it's greater then 0, if yes this rule is not valid. */
        /* otherwise, we will return a 10% of discount */
        return context.getEntryByName(getTargetIngredienteName())
                .map(entry -> {
                            if (baconQty > 0) {
                                return 0.0;
                            }
                            return totalPrice * PERCENTAGE_OF_DISCOUNT;
                        }
                ).orElse(0.0);
    }


}
