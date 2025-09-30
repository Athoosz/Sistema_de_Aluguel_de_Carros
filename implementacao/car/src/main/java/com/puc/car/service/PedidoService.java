package com.puc.car.service;

import org.springframework.stereotype.Service;

import com.puc.car.dto.Pedido.PedidoRequestRegister;
import com.puc.car.exceptions.EntityNotFoundException;
import com.puc.car.models.Cliente;
import com.puc.car.models.Pedido;
import com.puc.car.models.enums.EstadoPedido;
import com.puc.car.repositories.AgenteRepository;
import com.puc.car.repositories.AutomovelRepository;
import com.puc.car.repositories.ClienteRepository;
import com.puc.car.repositories.PedidoRepository;
import com.puc.car.models.Agente;
import com.puc.car.models.Automovel;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;
    private AgenteRepository agenteRepository;
    private ClienteRepository clienteRepository;
    private AutomovelRepository automovelRepository;

    public Pedido gerarPedido(PedidoRequestRegister pedidoRequestRegister){
        if(pedidoRepository.existsByNumPedido(pedidoRequestRegister.numPedido()))
            throw new IllegalStateException("Não é possível registrar um pedido existente");
        Pedido pedido = new Pedido();
        pedido.setNumPedido(pedidoRequestRegister.numPedido());
        Cliente cliente = clienteRepository
            .findByEmail(pedidoRequestRegister.emailCliente())
            .orElseThrow(() -> new EntityNotFoundException(
                "Usuário com email" + pedidoRequestRegister.emailCliente() + "não existe"
            ));
            
        Agente agente = agenteRepository
            .findByEmail(pedidoRequestRegister.emailAgente())
            .orElseThrow(() -> new EntityNotFoundException(
                "Usuário com email" + pedidoRequestRegister.emailAgente() + "não existe"
            ));

        Automovel automovel = automovelRepository
            .findById(pedidoRequestRegister.automovelId())
            .orElseThrow(() -> new EntityNotFoundException("Automovel com esse id não identificado"));

        pedido.setAgente(agente);
        pedido.setCliente(cliente);
        pedido.setAutomovel(automovel);
        pedido.setEstadoPedido(EstadoPedido.ANDAMENTO);
        pedido.setValorTotal(pedidoRequestRegister.valor());
        //contrato não é definido inicialmente
        return pedido;
    }

    public Pedido getPedido(String numPedido){
        return pedidoRepository.findByNumPedido(numPedido)
                        .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
    }

   public void deletePedido(Long id){
        if(pedidoRepository.existsById(id))
            pedidoRepository.deleteById(id);
        throw new EntityNotFoundException("Pedido não encontrado");
   }
    
}
