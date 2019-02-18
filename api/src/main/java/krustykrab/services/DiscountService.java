package krustykrab.services;

import krustykrab.model.Ingrediente;
import krustykrab.model.Lanche;
import krustykrab.promotion.IngredienteContext;
import krustykrab.rules.Rule;
import krustykrab.rules.RuleLight;
import krustykrab.rules.RuleMuitaCarne;
import krustykrab.rules.RuleMuitoQueijo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static krustykrab.utils.IngredienteUtils.getIngredienteQuantity;
import static krustykrab.utils.IngredienteUtils.getTotalPrice;

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
