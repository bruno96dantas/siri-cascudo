package com.nogueira.krusty.krab.services;

import com.nogueira.krusty.krab.model.Lanche;
import com.nogueira.krusty.krab.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private LancheService lancheService;

    public Pedido getLanchesInPedido(List<Lanche> lanches) {

        Pedido pedido = Pedido.builder()
                .lanches(lanches)
                .price(getPriceInPedido(lanches))
                .build();

        return pedido;
    }

    private BigDecimal getPriceInPedido(List<Lanche> lanches) {

        return lanches.stream()
                .map(lanche -> lancheService.calculatePrice(lanche))
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal("0.0"));
    }

}
