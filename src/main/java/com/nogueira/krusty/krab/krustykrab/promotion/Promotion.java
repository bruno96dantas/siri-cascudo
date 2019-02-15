package com.nogueira.krusty.krab.krustykrab.promotion;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(STRING)
    private DiscontType discontType;

    private Double discountValue;

    @OneToOne
    private Rule rule;

    public Double getDiscont(Double totalPrice, IngredienteContext context){
        return null;
    }
}
