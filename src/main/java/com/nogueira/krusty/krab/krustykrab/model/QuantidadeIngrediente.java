package com.nogueira.krusty.krab.krustykrab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

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

    public Double getTotalPrice() {
        return quatity * ingrediente.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuantidadeIngrediente that = (QuantidadeIngrediente) o;
        return lanche.equals(that.lanche) &&
                ingrediente.equals(that.ingrediente) &&
                quatity.equals(that.quatity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lanche, ingrediente, quatity);
    }
}
