package com.nogueira.krusty.krab.krustykrab.promotion;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@DiscriminatorValue("AND")
@Data
@Slf4j
public class AndRule extends Rule {

    @Builder
    public AndRule(Long id, Rule parent, Set<Rule> children, String targetIngredientName) {
        super(id, Operator.AND, parent, children, targetIngredientName);
    }


    @Override
    public Double getDiscont(Double totalPrice) {
        return null;
    }

    @Override
    public void validate() {
        if (!atLeastTwoChilddren.test(this)) {
            throw new RuntimeException("And Rule must have at least two children. ConditionId: " + id);
        }
    }
}
