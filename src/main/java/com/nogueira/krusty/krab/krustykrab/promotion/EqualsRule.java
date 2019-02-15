package com.nogueira.krusty.krab.krustykrab.promotion;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static java.util.Collections.emptySet;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@DiscriminatorValue("EQUALS")
@Data
@Slf4j
public class EqualsRule extends Rule {

    @Builder
    public EqualsRule(Long id, Rule parent, String targetIngredientName) {
        super(id, Operator.EQUALS, parent, emptySet(), targetIngredientName);
    }

    @Override
    public void validate() {
        if (hasChildren.test(this)) {
            throw new RuntimeException("EqualsRule can't have child");
        }
    }

    @Override
    public boolean evaluate(IngredienteContext context) {
        return false;
    }


}
