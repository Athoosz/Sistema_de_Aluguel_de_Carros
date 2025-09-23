package com.puc.car.service;

import com.puc.car.models.Pedido;
import com.puc.car.models.enums.EstadoPedido;
import com.puc.car.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criarPedido(Pedido pedido) {
        pedido.setEstadoPedido(EstadoPedido.ANDAMENTO);
        return pedidoRepository.save(pedido);
    }

    public Pedido buscarPorId(UUID id) {
        return pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> buscarPorClienteId(UUID clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public List<Pedido> buscarPorAgenteId(UUID agenteId) {
        // Para pedidos não há relação direta com agente, então retorna vazio
        return List.of();
    }

    public List<Pedido> buscarPorEstado(EstadoPedido estado) {
        return pedidoRepository.findByEstadoPedido(estado);
    }

    public Pedido aprovarPedido(UUID id) {
        Pedido pedido = buscarPorId(id);
        pedido.setEstadoPedido(EstadoPedido.APROVADO);
        return pedidoRepository.save(pedido);
    }

    public Pedido atualizarEstado(UUID id, EstadoPedido novoEstado) {
        Pedido pedido = buscarPorId(id);
        pedido.setEstadoPedido(novoEstado);
        return pedidoRepository.save(pedido);
    }

    public Pedido atualizarPedido(UUID id, Pedido pedidoAtualizado) {
        Pedido pedido = buscarPorId(id);
        
        // Atualizar apenas campos permitidos
        if (pedidoAtualizado.getValorTotal() != null) {
            pedido.setValorTotal(pedidoAtualizado.getValorTotal());
        }
        
        return pedidoRepository.save(pedido);
    }

    public Pedido cancelarPedido(UUID id) {
        Pedido pedido = buscarPorId(id);
        if (pedido.getEstadoPedido() == EstadoPedido.ANDAMENTO) {
            pedido.setEstadoPedido(EstadoPedido.REJEITADO);
            return pedidoRepository.save(pedido);
        } else {
            throw new RuntimeException("Pedido não pode ser cancelado no estado atual");
        }
    }

    public void removerPedido(UUID id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
    }
}