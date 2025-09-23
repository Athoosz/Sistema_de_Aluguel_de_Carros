package com.puc.car.service;

import com.puc.car.models.Contrato;
import com.puc.car.models.enums.TipoContrato;
import com.puc.car.repositories.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    public Contrato criarContrato(Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    public Contrato buscarPorId(UUID id) {
        return contratoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));
    }

    public List<Contrato> listarTodos() {
        return contratoRepository.findAll();
    }

    public List<Contrato> buscarPorClienteId(UUID clienteId) {
        return contratoRepository.findByClienteId(clienteId);
    }

    public List<Contrato> buscarPorAgenteId(UUID agenteId) {
        return contratoRepository.findByAgenteId(agenteId);
    }

    public List<Contrato> buscarPorAutomovelId(UUID automovelId) {
        return contratoRepository.findByAutomovelId(automovelId);
    }

    public List<Contrato> buscarPorTipo(TipoContrato tipo) {
        return contratoRepository.findByTipoContrato(tipo);
    }

    public Contrato buscarPorPedidoId(UUID pedidoId) {
        return contratoRepository.findByPedidoId(pedidoId)
            .orElseThrow(() -> new RuntimeException("Contrato não encontrado para este pedido"));
    }

    public Contrato atualizarContrato(UUID id, Contrato contratoAtualizado) {
        Contrato contrato = buscarPorId(id);
        contrato.setTipoContrato(contratoAtualizado.getTipoContrato());
        contrato.setValor(contratoAtualizado.getValor());
        return contratoRepository.save(contrato);
    }

    public void removerContrato(UUID id) {
        if (!contratoRepository.existsById(id)) {
            throw new RuntimeException("Contrato não encontrado");
        }
        contratoRepository.deleteById(id);
    }

    public Contrato alterarContrato(UUID id, Contrato dadosAlteracao) {
        Contrato contrato = buscarPorId(id);
        contrato.setTipoContrato(dadosAlteracao.getTipoContrato());
        contrato.setValor(dadosAlteracao.getValor());
        return contratoRepository.save(contrato);
    }
}