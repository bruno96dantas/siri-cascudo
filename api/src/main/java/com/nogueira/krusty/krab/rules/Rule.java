package com.nogueira.krusty.krab.rules;

import com.nogueira.krusty.krab.model.Ingrediente;
import com.nogueira.krusty.krab.unit.promotion.IngredienteContext;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Rule {

    /**
     * Esse é o campo que vai armazenar qual ingrediente vai ser o alvo.
     * No caso da RuleLight é o ALFACE.
     */
    private Ingrediente targetIngrediente;

    /**
     * Returna o desconto se applicavel ou 0.0
     */
    public abstract Double getDiscount(IngredienteContext context);

}
