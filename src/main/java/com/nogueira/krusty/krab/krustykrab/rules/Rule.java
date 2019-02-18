package com.nogueira.krusty.krab.krustykrab.rules;

import com.nogueira.krusty.krab.krustykrab.model.Ingrediente;
import com.nogueira.krusty.krab.krustykrab.promotion.IngredienteContext;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public abstract class Rule {

    private Ingrediente targetIngrediente;

    public abstract Double getDiscount(IngredienteContext context, BigDecimal totalPrice);

}
