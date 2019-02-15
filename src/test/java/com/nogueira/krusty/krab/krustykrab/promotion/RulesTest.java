package com.nogueira.krusty.krab.krustykrab.promotion;


import com.nogueira.krusty.krab.krustykrab.model.Ingrediente;
import com.nogueira.krusty.krab.krustykrab.model.QuantidadeIngrediente;
import com.nogueira.krusty.krab.krustykrab.rules.RuleLight;
import com.nogueira.krusty.krab.krustykrab.rules.RuleMain;
import com.nogueira.krusty.krab.krustykrab.rules.RuleMuitaCarne;
import com.nogueira.krusty.krab.krustykrab.rules.RuleMuitoQueijo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RulesTest {

    private List<RuleMain> rules;

    @Before
    public void setUp() {
        rules = Stream.of(new RuleLight(), new RuleMuitoQueijo(), new RuleMuitaCarne())
                .collect(toList());

    }

    @Test
    public void shouldGetPriceCorrectly(){

        Ingrediente queijo = Ingrediente.builder()
                .name("QUEIJO")
                .price(1.0)
                .build();

        Ingrediente carne = Ingrediente.builder()
                .name("CARNE")
                .price(1.0)
                .build();

        Ingrediente alface = Ingrediente.builder()
                .name("ALFACE")
                .price(1.0)
                .build();

        Map<String, Integer> context = new HashMap<>();
        context.put(carne.getName(), 6);
        context.put(queijo.getName(), 3);
        context.put(alface.getName(), 1);
        context.put("BACON", 1);

        IngredientesContext build = IngredientesContext.builder().context(context).build();

        double total_price = 10.0;

        List<Ingrediente> ingredientes = Arrays.asList(queijo, alface, carne);

        double total_discount = rules.stream()
                .map(rule -> rule.getDiscount(build, carne, total_price))
                .mapToDouble(Double::doubleValue)
                .sum();

    }


    private Function<Ingrediente, QuantidadeIngrediente> qtdeIngredientesSupplier = (x) -> QuantidadeIngrediente.builder()
            .ingrediente(x)
            .quatity(1)
            .build();
}
