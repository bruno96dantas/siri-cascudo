package com.nogueira.krusty.krab.krustykrab.promotion;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.function.Predicate;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Table(name = "rule")
@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "operator")
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Rule {

    protected static final Predicate<Rule> hasChildren = rule -> rule.getChildren() != null
            && !rule.getChildren().isEmpty();

    protected static final Predicate<Rule> atLeastTwoChilddren = hasChildren
            .and(rule -> rule.getChildren().size() > 1);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(STRING)
    @Column(insertable = false, updatable = false)
    protected Operator operator;

    @ManyToOne
    @JoinColumn(name = "parentId")
    protected Rule parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    protected Set<Rule> children;

    private String targetIngredienteName;

    public boolean isTargetIngrediente(String ingredienteName) {
        return ingredienteName.equalsIgnoreCase(getTargetIngredienteName());
    }

    public abstract void validate();

    public abstract boolean evaluate(IngredientesContext context);



}
