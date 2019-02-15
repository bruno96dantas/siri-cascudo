package com.nogueira.krusty.krab.krustykrab.promotion;

import com.nogueira.krusty.krab.krustykrab.model.QuantidadeIngrediente;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;


@Component
public class RuleManager {

    private final List<Rule> rules = singletonList(new LightRule());

    public Double calculate(Set<QuantidadeIngrediente> ingredientes) {

        Map<String, Integer> contex = ingredientes.stream()
                .collect(Collectors.toMap(e -> e.getIngrediente().getName().toUpperCase(), QuantidadeIngrediente::getQuatity));

        return ingredientes.stream()
                .map(i -> getIngredientValue(i, contex))
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private Double getIngredientValue(QuantidadeIngrediente ingrediente, Map<String, Integer> contex){

        return rules.stream()
                .map(rule -> rule.getPrice(ingrediente, contex))
                .filter(Optional::isPresent)
                .findFirst()
                .map(Optional::get)
                .orElse(ingrediente.getTotalPrice());
    }
}
