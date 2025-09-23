package com.puc.car.controllers;

import com.puc.car.models.Cliente;
import com.puc.car.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @GetMapping("/profile")
  public ResponseEntity<String> getProfile(Authentication authentication) {
    return ResponseEntity.ok("Profile do cliente: " + authentication.getName());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> buscarClientePorId(@PathVariable UUID id) {
    try {
      Cliente cliente = clienteService.buscarClientePorId(id);
      return ResponseEntity.ok(cliente);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  public ResponseEntity<List<?>> listarTodosClientes() {
    List<?> clientes = clienteService.listarTodosClientes();
    return ResponseEntity.ok(clientes);
  }

  @PutMapping("/{id}/dados-pessoais")
  public ResponseEntity<Cliente> atualizarDadosPessoais(
      @PathVariable UUID id, 
      @RequestParam String nome,
      @RequestParam String endereco,
      @RequestParam String profissao) {
    try {
      Cliente cliente = clienteService.atualizarDadosPessoais(id, nome, endereco, profissao);
      return ResponseEntity.ok(cliente);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
