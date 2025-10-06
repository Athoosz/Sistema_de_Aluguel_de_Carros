package com.puc.car.service;

import com.puc.car.dto.Pedido.PedidoRegisterRequest;
import com.puc.car.exceptions.EntityNotFoundException;
import com.puc.car.models.Agente;
import com.puc.car.models.Automovel;
import com.puc.car.models.Cliente;
import com.puc.car.models.Pedido;
import com.puc.car.models.enums.EstadoPedido;
import com.puc.car.repositories.AgenteRepository;
import com.puc.car.repositories.AutomovelRepository;
import com.puc.car.repositories.ClienteRepository;
import com.puc.car.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

  @Autowired private PedidoRepository pedidoRepository;
  @Autowired private AgenteRepository agenteRepository;
  @Autowired private ClienteRepository clienteRepository;
  @Autowired private AutomovelRepository automovelRepository;

  public Pedido gerarPedido(PedidoRegisterRequest pedidoRequestRegister) {
    if (pedidoRepository.existsByNumPedido(pedidoRequestRegister.numPedido()))
      throw new IllegalStateException("Não é possível registrar um pedido existente");
    Pedido pedido = new Pedido();
    pedido.setNumPedido(pedidoRequestRegister.numPedido());
    Cliente cliente =
        clienteRepository
            .findByEmail(pedidoRequestRegister.emailCliente())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "Usuário com email" + pedidoRequestRegister.emailCliente() + "não existe"));
    Agente agente =
        agenteRepository
            .findByEmail(pedidoRequestRegister.emailAgente())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "Usuário com email" + pedidoRequestRegister.emailAgente() + "não existe"));
    Automovel automovel =
        automovelRepository
            .findById(pedidoRequestRegister.automovelId())
            .orElseThrow(
                () -> new EntityNotFoundException("Automovel com esse id não identificado"));
    pedido.setAgente(agente);
    pedido.setCliente(cliente);
    pedido.setAutomovel(automovel);
    pedido.setEstadoPedido(EstadoPedido.ANDAMENTO);
    pedido.setValorTotal(pedidoRequestRegister.valor());
    // contrato não é definido inicialmente
    return pedidoRepository.save(pedido);
  }

  public Pedido aceitarPedido(Long id, String token) {
    com.auth0.jwt.interfaces.DecodedJWT jwt = com.auth0.jwt.JWT.decode(token);
    String role = jwt.getClaim("role").asString();
    if (!"AGENTE".equals(role)) {
      throw new RuntimeException("Apenas agentes podem aceitar pedidos");
    }
    Pedido pedido =
        pedidoRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
    pedido.setEstadoPedido(EstadoPedido.APROVADO);
    return pedidoRepository.save(pedido);
  }

  public Pedido rejeitarPedido(Long id, String token) {
    com.auth0.jwt.interfaces.DecodedJWT jwt = com.auth0.jwt.JWT.decode(token);
    String role = jwt.getClaim("role").asString();
    if (!"AGENTE".equals(role)) {
      throw new RuntimeException("Apenas agentes podem rejeitar pedidos");
    }
    Pedido pedido =
        pedidoRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
    pedido.setEstadoPedido(EstadoPedido.REJEITADO);
    return pedidoRepository.save(pedido);
  }

  public Pedido getPedido(String numPedido) {
    return pedidoRepository
        .findByNumPedido(numPedido)
        .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
  }

  public void deletePedido(Long id, String token) {
      com.auth0.jwt.interfaces.DecodedJWT jwt = com.auth0.jwt.JWT.decode(token);
      String email = jwt.getSubject();
      String role = jwt.getClaim("role").asString();
      if (!"CLIENTE".equals(role)) {
          throw new RuntimeException("Apenas clientes podem remover pedidos");
      }
      Pedido pedido = pedidoRepository.findById(id).orElse(null);
      if (pedido == null
              || pedido.getCliente() == null
              || !email.equals(pedido.getCliente().getEmail())) {
          throw new RuntimeException("Você só pode remover seus próprios pedidos");
      }
      pedidoRepository.deleteById(id);
  }
  
   public Pedido atualizarPedido(Long id, PedidoRegisterRequest pedidoRequestRegister) {
    Pedido pedido =
        pedidoRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
    // Atualiza apenas campos permitidos
    pedido.setValorTotal(pedidoRequestRegister.valor());
    // Se quiser permitir atualização de automóvel, agente, etc, adicione aqui
    return pedidoRepository.save(pedido);
  }

  public Pedido getPedidoById(Long id) {
    return pedidoRepository.findById(id).orElse(null);
  }
}
