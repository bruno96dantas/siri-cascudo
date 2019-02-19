package krustykrab.model;

import krustykrab.utils.IngredienteUtils;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class Lanche {

    private String name;
    private List<Ingrediente> ingredientes;
    private BigDecimal price;

    @Builder
    public Lanche(String name, List<Ingrediente> ingredientes) {
        this.name = name;
        this.ingredientes = ingredientes;
        this.price = getTotalPrice();
    }

    public BigDecimal getTotalPrice() {
        return IngredienteUtils.getTotalPrice(getIngredientes());
    }

    @Override
    public String toString() {
        return "Lanche{" +
                "name='" + name + '\'' +
                ", ingredientes=" + ingredientes +
                '}';
    }
}