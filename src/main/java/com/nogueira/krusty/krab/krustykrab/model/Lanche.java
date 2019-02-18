package com.nogueira.krusty.krab.krustykrab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lanche {

    private String name;

    private List<Ingrediente> ingredientes;

    public Map<Ingrediente, Integer> getIngredienteQuantity() {
        // why long? because it is the biggest 64-bit primitive value that java has
        return ingredientes.stream()
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet()
                .stream()
                .collect(toMap(Map.Entry::getKey, entry -> entry.getValue().intValue()));

    }

    public BigDecimal getTotalPrice() {
        return getIngredienteQuantity().entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return "Lanche{" +
                "name='" + name + '\'' +
                ", ingredientes=" + ingredientes +
                '}';
    }
}
