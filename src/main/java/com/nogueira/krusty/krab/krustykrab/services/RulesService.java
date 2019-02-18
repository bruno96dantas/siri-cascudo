package com.nogueira.krusty.krab.krustykrab.services;

import com.nogueira.krusty.krab.krustykrab.promotion.IngredienteContext;
import com.nogueira.krusty.krab.krustykrab.rules.Rule;
import com.nogueira.krusty.krab.krustykrab.rules.RuleLight;
import com.nogueira.krusty.krab.krustykrab.rules.RuleMuitaCarne;
import com.nogueira.krusty.krab.krustykrab.rules.RuleMuitoQueijo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;

@Component
public class RulesService {

    private final List<Rule> applicableRules = asList(new RuleLight(), new RuleMuitoQueijo(), new RuleMuitaCarne());


    public Double getDiscount(BigDecimal lanchePrice, IngredienteContext context) {
        return applicableRules.stream()
                .map(rule -> rule.getDiscount(context, lanchePrice))
                .mapToDouble(Double::doubleValue)
                .sum();
    }


}
