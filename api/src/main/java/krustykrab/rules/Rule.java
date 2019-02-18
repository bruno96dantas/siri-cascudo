package krustykrab.rules;

import krustykrab.model.Ingrediente;
import krustykrab.promotion.IngredienteContext;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Rule {

    private Ingrediente targetIngrediente;

    public abstract Double getDiscount(IngredienteContext context);

}
