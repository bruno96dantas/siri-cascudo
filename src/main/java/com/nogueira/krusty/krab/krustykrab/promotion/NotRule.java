package com.nogueira.krusty.krab.krustykrab.promotion;

import com.google.common.collect.ImmutableSet;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@DiscriminatorValue("NOT")
@Data
@Slf4j
public class NotRule extends Rule {


    @Builder
    public NotRule(Long id, Rule parent, Rule children, String targetIngredientName) {
        super(id, Operator.NOT, parent, ImmutableSet.of(children), targetIngredientName);
    }

    @Override
    public void validate() {
        if (!hasChildren.test(this)) {
            throw new RuntimeException("Not rule must have at least one child");
        }
    }

    @Override
    public boolean evaluate(IngredientesContext context) {
        return false;
    }


    public Double getDiscont(Double totalPrice) {
        return null;
    }
}
