package com.nogueira.krusty.krab.integration.services;

import com.nogueira.krusty.krab.model.Lanche;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardapioTestIT extends TestCommon {

    @Test
    public void shouldBeAbleToFetchCardapio(){

        ResponseEntity<List<Lanche>> lanchesInCardapio = krustyKrabClient.getLanchesInCardapio();

        List<Lanche> lanches = lanchesInCardapio.getBody();

        assertThat(lanches).isNotNull();

        assertThat(lanches.size()).isEqualTo(4);
    }
}
