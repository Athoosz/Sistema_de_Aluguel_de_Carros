package com.puc.car.controllers;

import com.puc.car.dto.LoginRequest;
import com.puc.car.dto.RegisterAgenteRequest;
import com.puc.car.dto.RegisterClienteRequest;
import com.puc.car.dto.Response;
import com.puc.car.infra.security.TokenService;
import com.puc.car.models.Usuario;
import com.puc.car.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
  
  @Autowired
  private UsuarioService usuarioService;
  
  private final PasswordEncoder passwordEncoder;
  private final TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<Response> login(@RequestBody LoginRequest body) {
    try {
      Usuario user = usuarioService.buscarPorEmail(body.email());
      
      if (passwordEncoder.matches(body.senha(), user.getSenha())) {
        String token = this.tokenService.generateToken(user);
        return ResponseEntity.ok(new Response(user.getEmail(), token));
      }
      return ResponseEntity.badRequest().build();
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping("/register/cliente")
  public ResponseEntity<Response> registerCliente(@RequestBody RegisterClienteRequest body) {
    try {
      Usuario savedUser = usuarioService.cadastrarCliente(body);
      String token = this.tokenService.generateToken(savedUser);
      return ResponseEntity.ok(new Response(savedUser.getEmail(), token));
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping("/register/agente")
  public ResponseEntity<Response> registerAgente(@RequestBody RegisterAgenteRequest body) {
    try {
      Usuario savedUser = usuarioService.cadastrarAgente(body);
      String token = this.tokenService.generateToken(savedUser);
      return ResponseEntity.ok(new Response(savedUser.getEmail(), token));
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
