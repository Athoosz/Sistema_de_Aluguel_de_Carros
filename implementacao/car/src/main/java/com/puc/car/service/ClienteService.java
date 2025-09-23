package com.puc.car.service;

import com.puc.car.models.Cliente;
import com.puc.car.models.Usuario;
import com.puc.car.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Cliente buscarClientePorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        if (!(usuario instanceof Cliente)) {
            throw new RuntimeException("Usuário não é um cliente");
        }
        
        return (Cliente) usuario;
    }

    public List<Usuario> listarTodosClientes() {
        return usuarioRepository.findAll().stream()
            .filter(usuario -> usuario instanceof Cliente)
            .toList();
    }

    public Cliente atualizarDadosPessoais(UUID id, String nome, String endereco, String profissao) {
        Cliente cliente = buscarClientePorId(id);
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setProfissao(profissao);
        return (Cliente) usuarioRepository.save(cliente);
    }

    // Métodos específicos para os casos de uso do cliente
    public void criarPedido() {
        // TODO: Implementar lógica de criação de pedido
    }

    public void consultarPedido(UUID pedidoId) {
        // TODO: Implementar lógica de consulta de pedido
    }

    public void cancelarPedido(UUID pedidoId) {
        // TODO: Implementar lógica de cancelamento de pedido
    }
}