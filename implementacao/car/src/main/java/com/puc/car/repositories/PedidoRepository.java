package com.puc.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puc.car.models.Pedido;
import java.util.Optional;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByNumPedido(String numPedido);
    boolean existsByNumPedido(String numPedido);
}
