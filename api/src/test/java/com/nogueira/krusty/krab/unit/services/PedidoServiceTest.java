package com.nogueira.krusty.krab.unit.services;

import com.nogueira.krusty.krab.model.Lanche;
import com.nogueira.krusty.krab.model.Pedido;
import com.nogueira.krusty.krab.services.LancheService;
import com.nogueira.krusty.krab.services.PedidoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.nogueira.krusty.krab.model.Ingrediente.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PedidoServiceTest {

    @Mock
    private LancheService lancheService;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    public void shouldGeneratePedido() {

        Lanche XTUDO = Lanche.builder()
                .name("XTUDO")
                .ingredientes(asList(OVO, HAMBURGER, ALFACE, BACON, QUEIJO))
                .build();

        Lanche XVEGGIE = Lanche.builder()
                .name("XVEGGIE")
                .ingredientes(asList(OVO, ALFACE, QUEIJO))
                .build();

        Pedido pedido = Pedido.builder()
                .lanches(asList(XTUDO, XVEGGIE))
                .build();

        assertThat(pedido.getLanches()).hasSize(2);
    }
}
