package com.puc.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.puc.car.models.Pedido;
import java.util.Optional;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByNumPedido(String numPedido);
    boolean existsByNumPedido(String numPedido);
    
    @Query("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id")
    Optional<Pedido> findByIdWithCliente(@Param("id") Long id);
}
