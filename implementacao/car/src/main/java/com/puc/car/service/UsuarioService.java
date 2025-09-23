package com.puc.car.service;

import com.puc.car.dto.RegisterAgenteRequest;
import com.puc.car.dto.RegisterClienteRequest;
import com.puc.car.models.Agente;
import com.puc.car.models.Cliente;
import com.puc.car.models.Usuario;
import com.puc.car.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrarCliente(RegisterClienteRequest dto) {
        // Validação de negócio
        if (usuarioRepository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        // Criação do cliente com senha criptografada
        Cliente cliente = new Cliente();
        cliente.setEmail(dto.email());
        cliente.setSenha(passwordEncoder.encode(dto.senha()));
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setRg(dto.rg());
        cliente.setEndereco(dto.endereco());
        cliente.setProfissao(dto.profissao());

        return usuarioRepository.save(cliente);
    }

    public Usuario cadastrarAgente(RegisterAgenteRequest dto) {
        // Validação de negócio
        if (usuarioRepository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        // Criação do agente com senha criptografada
        Agente agente = new Agente();
        agente.setEmail(dto.email());
        agente.setSenha(passwordEncoder.encode(dto.senha()));
        agente.setCnpj(dto.cnpj());
        agente.setTipoAgente(dto.tipoAgente());

        return usuarioRepository.save(agente);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario buscarPorId(java.util.UUID id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}