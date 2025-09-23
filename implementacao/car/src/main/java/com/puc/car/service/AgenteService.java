package com.puc.car.service;

import com.puc.car.models.Agente;
import com.puc.car.models.Usuario;
import com.puc.car.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AgenteService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Agente buscarAgentePorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Agente não encontrado"));
        
        if (!(usuario instanceof Agente)) {
            throw new RuntimeException("Usuário não é um agente");
        }
        
        return (Agente) usuario;
    }

    public List<Usuario> listarTodosAgentes() {
        return usuarioRepository.findAll().stream()
            .filter(usuario -> usuario instanceof Agente)
            .toList();
    }

    public Agente atualizarPerfil(UUID id, String cnpj) {
        Agente agente = buscarAgentePorId(id);
        agente.setCnpj(cnpj);
        return (Agente) usuarioRepository.save(agente);
    }

    // Métodos específicos para os casos de uso do agente
    public void avaliarPedido(UUID pedidoId, String decisao) {
        // TODO: Implementar lógica de avaliação de pedido
    }

    public void gerenciarAutomoveis(UUID automovelId) {
        // TODO: Implementar lógica de gerenciamento de automóveis
    }
}