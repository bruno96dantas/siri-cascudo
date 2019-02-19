package com.nogueira.krusty.krab.unit.services;

import com.nogueira.krusty.krab.unit.promotion.IngredienteContext;
import com.nogueira.krusty.krab.model.Ingrediente;
import com.nogueira.krusty.krab.model.Lanche;
import org.springframework.stereotype.Component;
import com.nogueira.krusty.krab.rules.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static com.nogueira.krusty.krab.model.utils.IngredienteUtils.getIngredienteQuantity;
import static com.nogueira.krusty.krab.model.utils.IngredienteUtils.getTotalPrice;

@Component
public class DiscountService {

    private final List<Rule> applicableRules = asList(new RuleLight(), new RuleMuitoQueijo(), new RuleMuitaCarne());

    public BigDecimal getDiscount(Lanche lanche) {

        return getDiscount(lanche.getIngredientes());

    }

    public BigDecimal getDiscount(List<Ingrediente> ingredientes) {

        Map<Ingrediente, Integer> contextMap = getIngredienteQuantity(ingredientes);

        BigDecimal totalPrice = getTotalPrice(ingredientes);

        IngredienteContext context = IngredienteContext.builder()
                .context(contextMap)
                .totalPrice(totalPrice)
                .build();

        return applicableRules.stream()
                .map(rule -> rule.getDiscount(context))
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
