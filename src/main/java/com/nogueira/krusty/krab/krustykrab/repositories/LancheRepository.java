package com.nogueira.krusty.krab.krustykrab.repositories;

import com.nogueira.krusty.krab.krustykrab.model.Lanche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancheRepository extends JpaRepository<Lanche, Long> {
}
