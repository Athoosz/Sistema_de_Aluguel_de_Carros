package com.puc.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puc.car.dto.Contrato.ContratoRegisterRequest;
import com.puc.car.exceptions.EntityNotFoundException;
import com.puc.car.models.Contrato;
import com.puc.car.models.Pedido;
import com.puc.car.repositories.ContratoRepository;
import com.puc.car.repositories.PedidoRepository;

import java.time.LocalDateTime;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public Contrato criarContrato(ContratoRegisterRequest request) {
        Pedido pedido = pedidoRepository.findById(request.pedidoId())
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        if (contratoRepository.existsByPedidoId(request.pedidoId())) {
            throw new IllegalStateException("Este pedido já possui um contrato associado");
        }

        Contrato contrato = new Contrato();
        contrato.setTipoContrato(request.tipoContrato());
        contrato.setValorTotal(request.valorTotal());
        contrato.setDataCriacao(LocalDateTime.now());
        contrato.setPedido(pedido);

        return contratoRepository.save(contrato);
    }

    public Contrato buscarPorId(Long id) {
        return contratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado"));
    }

    public Contrato buscarPorPedido(Long pedidoId) {
        return contratoRepository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado para este pedido"));
    }

    public Contrato atualizarContrato(Long id, ContratoRegisterRequest request) {
        Contrato contrato = buscarPorId(id);

        if (!contrato.getPedido().getId().equals(request.pedidoId()) && 
            contratoRepository.existsByPedidoId(request.pedidoId())) {
            throw new IllegalStateException("O pedido informado já possui um contrato associado");
        }

        Pedido pedido = pedidoRepository.findById(request.pedidoId())
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        contrato.setTipoContrato(request.tipoContrato());
        contrato.setValorTotal(request.valorTotal());
        contrato.setPedido(pedido);

        return contratoRepository.save(contrato);
    }

    public void deletarContrato(Long id) {
        if (!contratoRepository.existsById(id)) {
            throw new EntityNotFoundException("Contrato não encontrado");
        }
        contratoRepository.deleteById(id);
    }

    public void aprovarContrato(Long id) {
        Contrato contrato = buscarPorId(id);
        contrato.setAprovado(true);
        contrato.setDataAprovacao(LocalDateTime.now());
        contratoRepository.save(contrato);
    }

    public void rejeitarContrato(Long id) {
        Contrato contrato = buscarPorId(id);
        contrato.setAprovado(false);
        contrato.setDataAprovacao(LocalDateTime.now());
        contratoRepository.save(contrato);
    }
}