package com.puc.car.repositories;

import com.puc.car.models.Pedido;
import com.puc.car.models.enums.EstadoPedido;
import com.puc.car.models.Cliente;
import com.puc.car.models.Automovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    
    List<Pedido> findByCliente(Cliente cliente);
    List<Pedido> findByEstadoPedido(EstadoPedido estadoPedido);
    List<Pedido> findByAutomovel(Automovel automovel);
    List<Pedido> findByClienteAndEstadoPedido(Cliente cliente, EstadoPedido estadoPedido);
    List<Pedido> findByClienteId(UUID clienteId);
    List<Pedido> findByAutomovelId(UUID automovelId);
    List<Pedido> findByEstadoPedidoOrderByIdDesc(EstadoPedido estado);
    Long countByEstadoPedido(EstadoPedido estado);
}