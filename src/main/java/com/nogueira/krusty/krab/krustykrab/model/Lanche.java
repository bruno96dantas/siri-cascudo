package com.nogueira.krusty.krab.krustykrab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;
import java.util.function.Function;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "lanche")
public class Lanche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<QuantidadeIngrediente> ingredientes;

    public Double getTotalPrice() {
        return ingredientes.stream()
                .map(QuantidadeIngrediente::getTotalPrice)
                .mapToDouble(Double::doubleValue)
                .sum();
    }


}
