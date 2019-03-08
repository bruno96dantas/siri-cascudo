package com.nogueira.krusty.krab.controllers;

import com.nogueira.krusty.krab.model.Lanche;
import com.nogueira.krusty.krab.model.Pedido;
import com.nogueira.krusty.krab.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Pedido> getLanchesInPedido(@RequestBody List<Lanche> lanches) {

        return ResponseEntity.ok(pedidoService.getLanchesInPedido(lanches));
    }
}
