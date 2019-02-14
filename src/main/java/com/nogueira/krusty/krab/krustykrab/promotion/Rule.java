package com.nogueira.krusty.krab.krustykrab.promotion;

import com.nogueira.krusty.krab.krustykrab.model.Ingrediente;

import java.util.Optional;

public abstract class Rule {

    public abstract Optional<Float> getPrice(Ingrediente ingrediente);

    abstract String getTargetIngredienteName();

    boolean isTargetIngrediente(String ingredienteName){
        return Optional.ofNullable(ingredienteName)
                .map(name -> name.equalsIgnoreCase(getTargetIngredienteName()))
                .orElseThrow(() -> new RuntimeException("ingredienteName can't be null!"));
    }
}
