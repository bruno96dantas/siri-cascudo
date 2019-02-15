package com.nogueira.krusty.krab.krustykrab.rules;

import com.nogueira.krusty.krab.krustykrab.promotion.IngredienteContext;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class RuleMain {

    private String targetIngredienteName;

    public abstract Double getDiscount(IngredienteContext context, Double totalPrice);

}
