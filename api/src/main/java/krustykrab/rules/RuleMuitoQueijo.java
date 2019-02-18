package krustykrab.rules;


import krustykrab.model.Ingrediente;
import lombok.Builder;

@Builder
public class RuleMuitoQueijo extends RuleMuito {

    public RuleMuitoQueijo() {
        super(Ingrediente.QUEIJO);
    }
}
