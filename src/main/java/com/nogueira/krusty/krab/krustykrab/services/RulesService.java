package com.nogueira.krusty.krab.krustykrab.services;

import com.nogueira.krusty.krab.krustykrab.model.Ingrediente;
import com.nogueira.krusty.krab.krustykrab.model.Lanche;
import com.nogueira.krusty.krab.krustykrab.promotion.IngredienteContext;
import com.nogueira.krusty.krab.krustykrab.rules.Rule;
import com.nogueira.krusty.krab.krustykrab.rules.RuleLight;
import com.nogueira.krusty.krab.krustykrab.rules.RuleMuitaCarne;
import com.nogueira.krusty.krab.krustykrab.rules.RuleMuitoQueijo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

@Component
public class RulesService {

    private final List<Rule> applicableRules = asList(new RuleLight(), new RuleMuitoQueijo(), new RuleMuitaCarne());

    public BigDecimal getDiscount(Lanche lanche) {
        Map<Ingrediente, Integer> contextMap = lanche.getIngredienteQuantity();

        IngredienteContext context = IngredienteContext.builder()
                .context(contextMap)
                .build();

        BigDecimal lanchePrice = lanche.getTotalPrice();

        return applicableRules.stream()
                .map(rule -> rule.getDiscount(context, lanchePrice))
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
