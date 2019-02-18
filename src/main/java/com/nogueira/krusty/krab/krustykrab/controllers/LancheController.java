package com.nogueira.krusty.krab.krustykrab.controllers;

import com.nogueira.krusty.krab.krustykrab.model.Cardapio;
import com.nogueira.krusty.krab.krustykrab.model.Lanche;
import com.nogueira.krusty.krab.krustykrab.services.LancheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/lanches")
public class LancheController {

    @Autowired
    private LancheService lancheService;


    @GetMapping("/cardapio")
    public ResponseEntity<List<Lanche>> getLanchesInCardapio() {
        return ResponseEntity.ok(lancheService.getLanchesInCardapio());
    }


    /* get lan*/
    @PostMapping("/price")
    public ResponseEntity<BigDecimal> getLanchePrice(@RequestParam(value = "lanche") Cardapio cardapio) {
        return ResponseEntity.ok(cardapio.getLanche().getTotalPrice());
    }
}
