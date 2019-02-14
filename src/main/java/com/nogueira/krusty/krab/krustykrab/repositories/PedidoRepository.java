package com.nogueira.krusty.krab.krustykrab.repositories;

import com.nogueira.krusty.krab.krustykrab.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
