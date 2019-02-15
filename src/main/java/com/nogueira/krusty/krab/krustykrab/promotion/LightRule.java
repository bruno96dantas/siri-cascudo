package com.nogueira.krusty.krab.krustykrab.promotion;


import com.nogueira.krusty.krab.krustykrab.model.Ingrediente;
import com.nogueira.krusty.krab.krustykrab.model.QuantidadeIngrediente;

import java.util.Map;
import java.util.Optional;

public class LightRule extends Rule {

    private final static String BACON = "BACON";

    @Override
    public Optional<Double> getPrice(QuantidadeIngrediente quantidadeIngrediente, Map<String, Integer> context) {

        Ingrediente ingrediente = quantidadeIngrediente.getIngrediente();

        if (!isTargetIngrediente(ingrediente.getName()))
            return Optional.empty();

        Integer qtyBacon = context.getOrDefault(BACON, 0);

        Double price = ingrediente.getPrice();

        if (qtyBacon == 0) {
            price = price - (price * 0.1);
        }

        return Optional.of(price * quantidadeIngrediente.getQuatity());

    }


    @Override
    String getTargetIngredienteName() {
        return "QUEIJO";
    }


}
