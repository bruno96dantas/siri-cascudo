package com.nogueira.krusty.krab.rules;


import com.nogueira.krusty.krab.model.Ingrediente;
import lombok.Builder;

@Builder
public class RuleMuitoQueijo extends RuleMuito {

    public RuleMuitoQueijo() {
        super(Ingrediente.QUEIJO);
    }
}
