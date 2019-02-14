package com.nogueira.krusty.krab.krustykrab.repositories;

import com.nogueira.krusty.krab.krustykrab.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

}
