package com.puc.car.repositories;

import com.puc.car.models.Contrato;
import com.puc.car.models.enums.TipoContrato;
import com.puc.car.models.Cliente;
import com.puc.car.models.Agente;
import com.puc.car.models.Automovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, UUID> {
    
    List<Contrato> findByCliente(Cliente cliente);
    List<Contrato> findByAgente(Agente agente);
    List<Contrato> findByAutomovel(Automovel automovel);
    List<Contrato> findByTipoContrato(TipoContrato tipoContrato);
    Optional<Contrato> findByPedidoId(UUID pedidoId);
    List<Contrato> findByClienteId(UUID clienteId);
    List<Contrato> findByAgenteId(UUID agenteId);
    List<Contrato> findByAutomovelId(UUID automovelId);
    List<Contrato> findByAgenteIdAndTipoContrato(UUID agenteId, TipoContrato tipo);
    List<Contrato> findByClienteIdAndTipoContrato(UUID clienteId, TipoContrato tipo);
}