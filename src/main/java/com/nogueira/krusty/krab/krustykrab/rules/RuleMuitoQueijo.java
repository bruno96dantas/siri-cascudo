package com.nogueira.krusty.krab.krustykrab.rules;


import com.nogueira.krusty.krab.krustykrab.model.Ingrediente;
import lombok.Builder;

@Builder
public class RuleMuitoQueijo extends RuleMuito {

    public RuleMuitoQueijo() {
        super(Ingrediente.QUEIJO);
    }
}
