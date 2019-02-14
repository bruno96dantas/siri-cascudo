package com.nogueira.krusty.krab.krustykrab.promotion;


import com.nogueira.krusty.krab.krustykrab.model.Ingrediente;

import java.util.Optional;

public class LightRule extends Rule {

    @Override
    public Optional<Float> getPrice(Ingrediente ingrediente) {
//        if (!isTargetIngrediente(ingrediente.getName()))
        return Optional.empty();


    }

    @Override
    String getTargetIngredienteName() {
        return "QUEIJO";
    }


}
