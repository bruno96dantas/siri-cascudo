package com.nogueira.krusty.krab.krustykrab.services;


import com.nogueira.krusty.krab.krustykrab.model.Ingrediente;
import com.nogueira.krusty.krab.krustykrab.model.Lanche;
import org.assertj.core.data.Percentage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import static com.nogueira.krusty.krab.krustykrab.model.Ingrediente.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class LancheServiceTest {

    public static final Percentage PERCENTAGE = Percentage.withPercentage(0.1);
    Function<List<Ingrediente>, BigDecimal> valueSupplier = (x) -> x.stream()
            .map(Ingrediente::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    @InjectMocks
    private LancheService lancheService;

    @Test
    public void shouldGeneratePrice() {

    }

    @Test
    public void shouldGeneratePriceAndGetPromotionRuleLight() {
        List<Ingrediente> ingredientes = asList(ALFACE, HAMBURGER, HAMBURGER, OVO, QUEIJO);

        Lanche guilhermeEspecial = Lanche.builder()
                .ingredientes(ingredientes)
                .name("GuilhermeEspecial")
                .build();

        BigDecimal originalPrice = guilhermeEspecial.getTotalPrice();

        assertThat(originalPrice).isEqualTo(valueSupplier.apply(ingredientes));

        BigDecimal promotionPrice = lancheService.calculatePrice(guilhermeEspecial);

        assertThat(promotionPrice)
                .isCloseTo(originalPrice.subtract(originalPrice.multiply(BigDecimal.valueOf(0.1))), PERCENTAGE);
            // 10% of discount
    }

    @Test
    public void shouldGeneratePriceAndGetPromotionRuleMuitaCarne() {
        List<Ingrediente> ingredientes = asList(ALFACE, BACON, HAMBURGER, HAMBURGER, HAMBURGER, OVO, QUEIJO);

        Lanche guilhermeEspecial = Lanche.builder()
                .ingredientes(ingredientes)
                .name("GuilhermeEspecial")
                .build();

        BigDecimal originalPrice = guilhermeEspecial.getTotalPrice();

        assertThat(originalPrice).isEqualTo(valueSupplier.apply(ingredientes));

        BigDecimal promotionPrice = lancheService.calculatePrice(guilhermeEspecial);

        assertThat(promotionPrice)
                .isCloseTo(originalPrice.subtract(HAMBURGER.getPrice()), PERCENTAGE); // 1 Hamburguer for free


        ingredientes = asList(ALFACE, BACON, HAMBURGER, HAMBURGER, HAMBURGER, HAMBURGER, HAMBURGER, HAMBURGER, OVO, QUEIJO);

        guilhermeEspecial.setIngredientes(ingredientes);

        originalPrice = guilhermeEspecial.getTotalPrice();

        assertThat(originalPrice).isEqualTo(valueSupplier.apply(ingredientes));

        promotionPrice = lancheService.calculatePrice(guilhermeEspecial);

        assertThat(promotionPrice)
                .isCloseTo(originalPrice.subtract(HAMBURGER.getPrice().multiply(BigDecimal.valueOf(2.0))), PERCENTAGE); // 2 Hamburguer for free

    }

    @Test
    public void shouldGeneratePriceAndGetPromotionRuleMuitOQueijo() {
        List<Ingrediente> ingredientes = asList(ALFACE, BACON, QUEIJO, QUEIJO, QUEIJO, OVO);

        Lanche guilhermeEspecial = Lanche.builder()
                .ingredientes(ingredientes)
                .name("GuilhermeEspecial")
                .build();

        BigDecimal originalPrice = guilhermeEspecial.getTotalPrice();

        assertThat(originalPrice).isEqualTo(valueSupplier.apply(ingredientes));

        BigDecimal promotionPrice = lancheService.calculatePrice(guilhermeEspecial);

        assertThat(promotionPrice)
                .isCloseTo(originalPrice.subtract(QUEIJO.getPrice()), PERCENTAGE); // 1 QUEIJO for free

        ingredientes = asList(ALFACE, BACON, QUEIJO, QUEIJO, QUEIJO, QUEIJO, QUEIJO, QUEIJO, OVO);

        guilhermeEspecial.setIngredientes(ingredientes);

        originalPrice = guilhermeEspecial.getTotalPrice();

        assertThat(originalPrice).isEqualTo(valueSupplier.apply(ingredientes));

        promotionPrice = lancheService.calculatePrice(guilhermeEspecial);

        assertThat(promotionPrice)
                .isCloseTo(originalPrice.subtract(QUEIJO.getPrice().multiply(BigDecimal.valueOf(2.0))), PERCENTAGE); // 2 queijos for free

    }


    @Test
    public void shouldGeneratePriceCorrectlyWithAllPromotions() {
        List<Ingrediente> ingredientes = asList(ALFACE, HAMBURGER, HAMBURGER, HAMBURGER, OVO, QUEIJO, QUEIJO, QUEIJO);

        Lanche guilhermeEspecial = Lanche.builder()
                .ingredientes(ingredientes)
                .name("GuilhermeEspecial")
                .build();

        BigDecimal originalPrice = guilhermeEspecial.getTotalPrice();

        assertThat(originalPrice).isEqualTo(valueSupplier.apply(ingredientes));

        BigDecimal promotionPrice = lancheService.calculatePrice(guilhermeEspecial);

        assertThat(promotionPrice)
                .isCloseTo(originalPrice
                        .subtract(QUEIJO.getPrice())
                        .subtract(HAMBURGER.getPrice())
                        .subtract(originalPrice.multiply(BigDecimal.valueOf(0.1))), PERCENTAGE); // 1 QUEIJO for free
    }
}
