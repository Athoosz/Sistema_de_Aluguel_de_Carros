package com.puc.car.service;

import com.puc.car.dto.Contrato.ContratoRegisterRequest;
import com.puc.car.exceptions.EntityNotFoundException;
import com.puc.car.models.Agente;
import com.puc.car.models.Contrato;
import com.puc.car.models.Pedido;
import com.puc.car.models.enums.EstadoPedido;
import com.puc.car.models.enums.TipoAgente;
import com.puc.car.models.enums.TipoContrato;
import com.puc.car.repositories.ContratoRepository;
import com.puc.car.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContratoService {
  @Autowired private com.puc.car.repositories.AgenteRepository agenteRepository;

  @Autowired private TokenService tokenService;

  @Transactional
  public Contrato criarContrato(ContratoRegisterRequest request, String token) {

    String email = tokenService.validateToken(token);
    if (email == null || email.isEmpty()) {
      throw new RuntimeException("Token inválido");
    }
    java.util.Optional<Agente> agenteOpt = agenteRepository.findByEmail(email);
    if (agenteOpt.isEmpty()) {
      throw new RuntimeException("Apenas agentes podem criar contratos");
    }
    Agente agente = agenteOpt.get();
    TipoAgente tipoAgente = agente.getTipoAgente();
    TipoContrato tipoContrato = request.tipoContrato();
    if (tipoAgente == TipoAgente.BANCO && tipoContrato != TipoContrato.CREDITO) {
      throw new RuntimeException("Agente do tipo BANCO só pode criar contrato de CREDITO");
    }
    if (tipoAgente == TipoAgente.EMPRESA && tipoContrato != TipoContrato.ALUGUEL) {
      throw new RuntimeException("Agente do tipo EMPRESA só pode criar contrato de ALUGUEL");
    }
    // Lógica de criação do contrato
    Pedido pedido =
        pedidoRepository
            .findById(request.pedidoId())
            .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

    if (contratoRepository.existsByPedidoId(request.pedidoId())) {
      throw new IllegalStateException("Este pedido já possui um contrato associado");
    }

    pedido.setEstadoPedido(EstadoPedido.APROVADO);
    pedidoRepository.saveAndFlush(pedido);

    Contrato contrato = new Contrato();
    contrato.setTipoContrato(request.tipoContrato());
    contrato.setValorTotal(request.valorTotal());
    contrato.setDataCriacao(java.time.LocalDateTime.now());
    contrato.setPedido(pedido);

    return contratoRepository.save(contrato);
  }

  @Autowired private ContratoRepository contratoRepository;

  @Autowired private PedidoRepository pedidoRepository;

  public Contrato buscarPorId(Long id) {
    return contratoRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado"));
  }

  public Contrato buscarPorPedido(Long pedidoId) {
    return contratoRepository
        .findByPedidoId(pedidoId)
        .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado para este pedido"));
  }

  public Contrato atualizarContrato(Long id, ContratoRegisterRequest request) {
    Contrato contrato = buscarPorId(id);

    if (!contrato.getPedido().getId().equals(request.pedidoId())
        && contratoRepository.existsByPedidoId(request.pedidoId())) {
      throw new IllegalStateException("O pedido informado já possui um contrato associado");
    }

    Pedido pedido =
        pedidoRepository
            .findById(request.pedidoId())
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
}
