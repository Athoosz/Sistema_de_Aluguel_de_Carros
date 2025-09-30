package com.puc.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.puc.car.models.Contrato;
import com.puc.car.models.enums.TipoContrato;
import java.util.Optional;
import java.util.List;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    
    boolean existsByPedidoId(Long pedidoId);
    
    Optional<Contrato> findByPedidoId(Long pedidoId);
    
    List<Contrato> findByTipoContrato(TipoContrato tipoContrato);
    
    @Query("SELECT c FROM Contrato c WHERE c.pedido.cliente.id = :clienteId")
    List<Contrato> findByPedidoClienteId(@Param("clienteId") Long clienteId);
    
    @Query("SELECT c FROM Contrato c WHERE c.pedido.agente.id = :agenteId")
    List<Contrato> findByPedidoAgenteId(@Param("agenteId") Long agenteId);
    
    List<Contrato> findByAprovado(boolean aprovado);
}