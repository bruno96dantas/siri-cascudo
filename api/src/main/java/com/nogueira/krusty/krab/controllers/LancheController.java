package com.nogueira.krusty.krab.controllers;

import com.nogueira.krusty.krab.client.dto.IngredientesDto;
import com.nogueira.krusty.krab.model.Lanche;
import com.nogueira.krusty.krab.unit.services.LancheService;
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
    public ResponseEntity<BigDecimal> getLanchePrice(@RequestBody IngredientesDto dto) {
        return ResponseEntity.ok(lancheService.calculatePrice(dto.getIngredienteList()));
    }
}
