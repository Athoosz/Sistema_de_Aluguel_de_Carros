package com.puc.car.controllers;

import com.puc.car.dto.LoginRequestDTO;
import com.puc.car.dto.RegisterAgenteRequestDTO;
import com.puc.car.dto.RegisterClienteRequestDTO;
import com.puc.car.dto.ResponseDTO;
import com.puc.car.infra.security.TokenService;
import com.puc.car.models.Agente;
import com.puc.car.models.Cliente;
import com.puc.car.models.Usuario;
import com.puc.car.repositories.UsuarioRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final UsuarioRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
    Usuario user =
        this.repository
            .findByEmail(body.email())
            .orElseThrow(() -> new RuntimeException("User not found"));
    if (passwordEncoder.matches(body.senha(), user.getSenha())) {
      String token = this.tokenService.generateToken(user);
      return ResponseEntity.ok(new ResponseDTO(user.getEmail(), token));
    }
    return ResponseEntity.badRequest().build();
  }

  @PostMapping("/register/cliente")
  public ResponseEntity<ResponseDTO> registerCliente(@RequestBody RegisterClienteRequestDTO body) {
    Optional<Usuario> user = this.repository.findByEmail(body.email());

    if (user.isEmpty()) {
      Cliente newCliente = new Cliente();
      newCliente.setSenha(passwordEncoder.encode(body.senha()));
      newCliente.setEmail(body.email());
      newCliente.setCpf(body.cpf());
      newCliente.setRg(body.rg());
      newCliente.setNome(body.nome());
      newCliente.setEndereco(body.endereco());
      newCliente.setProfissao(body.profissao());

      Usuario savedUser = this.repository.save(newCliente);

      String token = this.tokenService.generateToken(savedUser);
      return ResponseEntity.ok(new ResponseDTO(savedUser.getEmail(), token));
    }
    return ResponseEntity.badRequest().build();
  }

  @PostMapping("/register/agente")
  public ResponseEntity<ResponseDTO> registerAgente(@RequestBody RegisterAgenteRequestDTO body) {
    Optional<Usuario> user = this.repository.findByEmail(body.email());

    if (user.isEmpty()) {
      Agente newAgente = new Agente();
      newAgente.setSenha(passwordEncoder.encode(body.senha()));
      newAgente.setEmail(body.email());
      newAgente.setCnpj(body.cnpj());
      newAgente.setTipoAgente(body.tipoAgente());

      Usuario savedUser = this.repository.save(newAgente);

      String token = this.tokenService.generateToken(savedUser);
      return ResponseEntity.ok(new ResponseDTO(savedUser.getEmail(), token));
    }
    return ResponseEntity.badRequest().build();
  }
}
