package com.nogueira.krusty.krab.unit.services;


import com.nogueira.krusty.krab.model.Cardapio;
import com.nogueira.krusty.krab.model.Lanche;
import com.nogueira.krusty.krab.services.DiscountService;
import com.nogueira.krusty.krab.services.LancheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class LancheServiceTest {


    @Mock
    private DiscountService discountService;

    @InjectMocks
    private LancheService lancheService;

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenDiscountIsGreaterThanValue() {

        Lanche lanche = Cardapio.XBACON.getLanche();

        doReturn(BigDecimal.TEN).when(discountService).getDiscount(eq(lanche));

        lancheService.calculatePrice(lanche);
    }
}
