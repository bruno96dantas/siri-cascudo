package com.nogueira.krusty.krab.unit.services;

import com.nogueira.krusty.krab.model.Cardapio;
import com.nogueira.krusty.krab.model.Ingrediente;
import com.nogueira.krusty.krab.model.Lanche;
import org.assertj.core.data.Percentage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static com.nogueira.krusty.krab.model.Ingrediente.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DiscountServiceTest {

    private static final Percentage PERCENTAGE = Percentage.withPercentage(0.01);

    private Function<List<Ingrediente>, BigDecimal> valueSupplier = (x) -> x.stream()
            .map(Ingrediente::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    @InjectMocks
    private DiscountService discountService;

    @Test
    public void shouldGeneratePriceAndGetPromotionRuleLight() {
        List<Ingrediente> ingredientes = asList(ALFACE, HAMBURGER, HAMBURGER, OVO, QUEIJO);

        Lanche guilhermeEspecial = Lanche.builder()
                .ingredientes(ingredientes)
                .name("GuilhermeEspecial")
                .build();

        BigDecimal originalPrice = guilhermeEspecial.getTotalPrice();

        BigDecimal discount = discountService.getDiscount(guilhermeEspecial);

        assertThat(discount)
                .isCloseTo(originalPrice.multiply(BigDecimal.valueOf(0.1)), PERCENTAGE);
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

        BigDecimal discount = discountService.getDiscount(guilhermeEspecial);

        assertThat(discount)
                .isCloseTo(HAMBURGER.getPrice(), PERCENTAGE); // 1 Hamburguer for free


        ingredientes = asList(ALFACE, BACON, HAMBURGER, HAMBURGER, HAMBURGER, HAMBURGER, HAMBURGER, HAMBURGER, OVO, QUEIJO);

        guilhermeEspecial.setIngredientes(ingredientes);

        originalPrice = guilhermeEspecial.getTotalPrice();

        assertThat(originalPrice).isEqualTo(valueSupplier.apply(ingredientes));

        discount = discountService.getDiscount(guilhermeEspecial);

        assertThat(discount)
                .isCloseTo(HAMBURGER.getPrice().multiply(BigDecimal.valueOf(2.0)), PERCENTAGE); // 2 Hamburguer for free

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

        BigDecimal discount = discountService.getDiscount(guilhermeEspecial);

        assertThat(discount)
                .isCloseTo(QUEIJO.getPrice(), PERCENTAGE); // 1 QUEIJO for free

        ingredientes = asList(ALFACE, BACON, QUEIJO, QUEIJO, QUEIJO, QUEIJO, QUEIJO, QUEIJO, OVO);

        guilhermeEspecial.setIngredientes(ingredientes);

        originalPrice = guilhermeEspecial.getTotalPrice();

        assertThat(originalPrice).isEqualTo(valueSupplier.apply(ingredientes));

        discount = discountService.getDiscount(guilhermeEspecial);

        assertThat(discount)
                .isCloseTo(QUEIJO.getPrice().multiply(BigDecimal.valueOf(2.0)), PERCENTAGE); // 2 queijos for free

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

        BigDecimal promotionPrice = discountService.getDiscount(guilhermeEspecial);

        assertThat(promotionPrice)
                .isCloseTo(BigDecimal.ZERO
                        .add(QUEIJO.getPrice())
                        .add(HAMBURGER.getPrice())
                        .add(originalPrice.multiply(BigDecimal.valueOf(0.1))), PERCENTAGE);
    }

    @Test
    public void shouldNotApplyDiscountWhenItsNotNecessary() {

        List<Lanche> lanches = asList(Cardapio.XBACON.getLanche(),
                Cardapio.XBURGER.getLanche(),
                Cardapio.XEGG.getLanche(),
                Cardapio.XEGGBACON.getLanche());

        BigDecimal totalDiscount = lanches.stream()
                .map(discountService::getDiscount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(totalDiscount).isCloseTo(BigDecimal.ZERO, PERCENTAGE);
    }

}
