package com.nogueira.krusty.krab.model.utils;

import com.nogueira.krusty.krab.model.Ingrediente;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class IngredienteUtils {

    /**
     * Eu criei essa função para fazer duas coisas, a primeira seria gerar o "Context" e a outra era para pegar o preço total
     * Para essa segunda seria mais facil calcular como lista mesmo mas como eu já tinha o mapa...
     * Basicamente ele conta as entries duplicadas, counting() retorna um float, estava tendo alguns problemas com imprecisão.
     * Esta feia essa conversão de long para int. #TODO
     *
     * @return
     */
    public static Map<Ingrediente, Integer> getIngredienteQuantity(List<Ingrediente> ingredientes) {
        // why long? because it is the biggest 64-bit primitive value that java has
        return ingredientes.stream()
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet()
                .stream()
                .collect(toMap(Map.Entry::getKey, entry -> entry.getValue().intValue()));

    }

    /**
     * Mutiplica a qutidade de entities X o preço das mesmas.
     *
     * @return
     */
    public static BigDecimal getTotalPrice(List<Ingrediente> ingredientes) {
        return getIngredienteQuantity(ingredientes).entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}