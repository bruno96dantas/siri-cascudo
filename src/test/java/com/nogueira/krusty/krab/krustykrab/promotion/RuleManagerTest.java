package com.nogueira.krusty.krab.krustykrab.promotion;


import com.nogueira.krusty.krab.krustykrab.model.Ingrediente;
import com.nogueira.krusty.krab.krustykrab.model.QuantidadeIngrediente;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RuleManagerTest {

    @InjectMocks
    private List<Ingrediente> ingredienteList;

    @Before
    public void setUp() {

        ingredienteList = IntStream.rangeClosed(1, 5)
                .boxed()
                .map(v -> Ingrediente.builder()
                        .name("Ingrediente" + v)
                        .price(1.0)
                        .build()
                ).collect(toList());

    }


    private Function<Ingrediente, QuantidadeIngrediente> qtdeIngredientesSupplier = (x) -> QuantidadeIngrediente.builder()
            .ingrediente(x)
            .quatity(1)
            .build();
}
