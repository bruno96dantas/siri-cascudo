package com.nogueira.krusty.krab.rules;

import com.nogueira.krusty.krab.model.Ingrediente;
import com.nogueira.krusty.krab.unit.promotion.IngredienteContext;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Rule {

    private Ingrediente targetIngrediente;

    public abstract Double getDiscount(IngredienteContext context);

}
