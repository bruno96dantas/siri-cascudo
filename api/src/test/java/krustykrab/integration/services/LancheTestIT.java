package krustykrab.integration.services;

import krustykrab.dto.IngredientesDto;
import krustykrab.model.Ingrediente;
import krustykrab.utils.IngredienteUtils;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static krustykrab.model.Ingrediente.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LancheTestIT extends TestCommon {

    @Test
    public void shouldBeAbleToGetPrice() {

        List<Ingrediente> ingredientes = Arrays.asList(HAMBURGER, OVO, BACON);

        IngredientesDto ingredientesDto = IngredientesDto.builder().ingredienteList(ingredientes).build();

        ResponseEntity<Double> price = krustyKrabClient.getPrice(ingredientesDto);

        assertThat(price.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(price.getBody()).isNotNull();

        Optional.ofNullable(price.getBody())
                .ifPresent(body -> assertThat(BigDecimal.valueOf(body)).isEqualTo(IngredienteUtils.getTotalPrice(ingredientes)));
    }
}
