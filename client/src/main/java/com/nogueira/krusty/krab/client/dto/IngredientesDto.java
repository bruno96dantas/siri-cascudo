package com.nogueira.krusty.krab.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nogueira.krusty.krab.model.Ingrediente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientesDto {

    @JsonProperty("ingredientes")
    List<Ingrediente> ingredienteList;
}
