package krustykrab.utils;

import krustykrab.model.Ingrediente;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class IngredienteUtils {

    public static Map<Ingrediente, Integer> getIngredienteQuantity(List<Ingrediente> ingredientes) {
        // why long? because it is the biggest 64-bit primitive value that java has
        return ingredientes.stream()
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet()
                .stream()
                .collect(toMap(Map.Entry::getKey, entry -> entry.getValue().intValue()));

    }

    public static BigDecimal getTotalPrice(List<Ingrediente> ingredientes) {
        return getIngredienteQuantity(ingredientes).entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}