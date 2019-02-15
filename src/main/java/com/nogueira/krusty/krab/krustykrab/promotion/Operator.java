package com.nogueira.krusty.krab.krustykrab.promotion;

import java.util.function.Supplier;

public enum Operator {

    AND(() -> AndRule.builder().build()),
    NOT(() -> NotRule.builder().build()),
    EQUALS(() -> EqualsRule.builder().build());

    /**
     * Builder from ConditionDto to Condition.
     */
    private Supplier<Rule> ruleBuilder;

    /**
     * Default constructor.
     *
     * @param ruleBuilder function that receives a dto and generates a Condition.
     */
    private Operator(Supplier<Rule> ruleBuilder) {
        this.ruleBuilder = ruleBuilder;
    }

    ;

    /**
     * Get condition instancec based in the operator type.
     *
     * @return Condition instance.
     */
    public Rule toRule() {
        return ruleBuilder.get();
    }
}
