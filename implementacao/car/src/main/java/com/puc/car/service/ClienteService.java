package com.puc.car.service;

import com.puc.car.models.Cliente;
import com.puc.car.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscarClientePorId(UUID id) {
        return clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente atualizarDadosPessoais(UUID id, String nome, String endereco, String profissao) {
        Cliente cliente = buscarClientePorId(id);
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setProfissao(profissao);
        return clienteRepository.save(cliente);
    }

    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public Cliente buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public void deletarCliente(UUID id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado");
        }
        clienteRepository.deleteById(id);
    }
}