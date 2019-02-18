package com.nogueira.krusty.krab.krustykrab.model;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.entry;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class LancheTest {


    @Test
    public void shouldCorrectlyGeneratePrice() {

        List<Ingrediente> ingredientes = asList(Ingrediente.HAMBURGER, Ingrediente.HAMBURGER);

        Lanche teste = Lanche.builder().name("teste").ingredientes(ingredientes).build();

        Map<Ingrediente, Integer> ingredienteQuantity = teste.getIngredienteQuantity();

        assertThat(ingredienteQuantity)
                .isNotEmpty()
                .containsOnly(entry(Ingrediente.HAMBURGER, 2));

        BigDecimal price = teste.getTotalPrice();

        assertThat(price).isEqualTo(BigDecimal.valueOf(6.0));

    }
}
