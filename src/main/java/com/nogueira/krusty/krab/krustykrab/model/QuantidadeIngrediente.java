package com.nogueira.krusty.krab.krustykrab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "quantidadeIngrediente")
public class QuantidadeIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Lanche lanche;

    @ManyToOne
    private Ingrediente ingrediente;

    private Integer quatity;

    public Float getTotalPrice() {
        return quatity * ingrediente.getPrice();
    }
}
