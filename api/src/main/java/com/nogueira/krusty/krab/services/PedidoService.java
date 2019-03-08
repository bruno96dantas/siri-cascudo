package com.nogueira.krusty.krab.services;

import com.nogueira.krusty.krab.model.Lanche;
import com.nogueira.krusty.krab.model.Pedido;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class PedidoService {

    public Pedido getLanchesInPedido(List<Lanche> lanches) {

        Pedido pedido = Pedido.builder()
                .lanches(lanches)
                .build();

        return pedido;
    }

}
