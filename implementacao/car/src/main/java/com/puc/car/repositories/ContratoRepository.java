package com.puc.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puc.car.models.Contrato;
import java.util.Optional;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    
    boolean existsByPedidoId(Long pedidoId);
    
    Optional<Contrato> findByPedidoId(Long pedidoId);
    
    
}