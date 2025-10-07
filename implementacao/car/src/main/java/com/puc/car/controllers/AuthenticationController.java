package com.puc.car.controllers;

import com.puc.car.dto.Usuario.AgenteRegisterRequest;
import com.puc.car.dto.Usuario.ClienteRegisterRequest;
import com.puc.car.dto.Usuario.LoginRequest;
import com.puc.car.dto.Usuario.LoginResponse;
import com.puc.car.models.Agente;
import com.puc.car.models.Cliente;
import com.puc.car.models.Usuario;
import com.puc.car.repositories.UsuarioRepository;
import com.puc.car.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((Usuario) auth.getPrincipal());

            Usuario usuario = (Usuario) auth.getPrincipal();
            return ResponseEntity.ok(new LoginResponse(token, usuario.getEmail(), usuario.getRole().name(), usuario.getId()));
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Credenciais inválidas");
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/register/cliente")
    public ResponseEntity<?> registerCliente(@RequestBody @Valid ClienteRegisterRequest data) {
        try {
            if (this.repository.existsByEmail(data.email())) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Este email já está cadastrado");
                return ResponseEntity.badRequest().body(error);
            }

            String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());

            Cliente cliente = new Cliente();
            cliente.setEmail(data.email());
            cliente.setSenha(encryptedPassword);
            cliente.setRole(Usuario.Role.CLIENTE);
            cliente.setCpf(data.cpf());
            cliente.setRg(data.rg());
            cliente.setNome(data.nome());
            cliente.setEndereco(data.endereco());
            cliente.setProfissao(data.profissao());

            this.repository.save(cliente);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Cliente registrado com sucesso");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Erro interno do servidor");
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @PostMapping("/register/agente")
    public ResponseEntity<?> registerAgente(@RequestBody @Valid AgenteRegisterRequest data) {
        try {
            if (this.repository.existsByEmail(data.email())) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Este email já está cadastrado");
                return ResponseEntity.badRequest().body(error);
            }

            String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());

            Agente agente = new Agente();
            agente.setEmail(data.email());
            agente.setSenha(encryptedPassword);
            agente.setRole(Usuario.Role.AGENTE);
            agente.setCnpj(data.cnpj());
            agente.setTipoAgente(data.tipoAgente());

            this.repository.save(agente);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Agente registrado com sucesso");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Erro interno do servidor");
            return ResponseEntity.internalServerError().body(error);
        }
    }
}