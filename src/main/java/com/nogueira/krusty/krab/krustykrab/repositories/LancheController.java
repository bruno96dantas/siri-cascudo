package com.nogueira.krusty.krab.krustykrab.repositories;

import com.nogueira.krusty.krab.krustykrab.model.Cardapio;
import com.nogueira.krusty.krab.krustykrab.model.Lanche;
import com.nogueira.krusty.krab.krustykrab.services.LancheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

//    @PostMapping
//    public ResponseEntity<BigDecimal> getLanchePrice(){
//        //TODO nao tenho certeza como irei receber o lanche
//        //receber no body a lista de ingredientes?
//        //Receber no body o lanche?
//        return ResponseEntity.ok().build();
//    }

    /* get lan*/
    @GetMapping("/price")
    public ResponseEntity<BigDecimal> getLanchePrice(@RequestParam(value = "lanche") Cardapio cardapio) {
        return ResponseEntity.ok(cardapio.getLanche().getTotalPrice());
    }
}
