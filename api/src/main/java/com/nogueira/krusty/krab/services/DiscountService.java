package com.nogueira.krusty.krab.services;

import com.nogueira.krusty.krab.model.Ingrediente;
import com.nogueira.krusty.krab.model.Lanche;
import com.nogueira.krusty.krab.rules.Rule;
import com.nogueira.krusty.krab.rules.RuleLight;
import com.nogueira.krusty.krab.promotion.IngredienteContext;
import com.nogueira.krusty.krab.rules.RuleMuito.RuleMuitaCarne;
import com.nogueira.krusty.krab.rules.RuleMuito.RuleMuitoQueijo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.nogueira.krusty.krab.model.utils.IngredienteUtils.getIngredienteQuantity;
import static com.nogueira.krusty.krab.model.utils.IngredienteUtils.getTotalPrice;
import static java.util.Arrays.asList;

/**
 * Essa classe gerencia as regras de desconto disponiveis
 */
@Component
public class DiscountService {

    private final List<Rule> applicableRules = asList(new RuleLight(), new RuleMuitoQueijo(), new RuleMuitaCarne());

    public BigDecimal getDiscount(Lanche lanche) {

        return getDiscount(lanche.getIngredientes());

    }

    /**
     * Essa função pega todas as regras disponivels e testa se o contexto atual da match com a mesma.
     * Caso sim, essa vai retornar um valor BigDecimal que vai ser o valor do desconto.
     * Soma todos os resultados e retornar um disconto total
     */
    public BigDecimal getDiscount(List<Ingrediente> ingredientes) {

        Map<Ingrediente, Integer> contextMap = getIngredienteQuantity(ingredientes);

        BigDecimal totalPrice = getTotalPrice(ingredientes);

        IngredienteContext context = IngredienteContext.builder()
                .context(contextMap)
                .totalPrice(totalPrice)
                .build();

        /* Sum all applicable discounts */
        return applicableRules.stream()
                .map(rule -> rule.getDiscount(context))
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
