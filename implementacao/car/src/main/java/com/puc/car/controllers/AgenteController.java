package com.puc.car.controllers;

import com.puc.car.models.Agente;
import com.puc.car.service.AgenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agente")
public class AgenteController {

  @Autowired
  private AgenteService agenteService;

  @GetMapping("/profile")
  public ResponseEntity<String> getProfile(Authentication authentication) {
    return ResponseEntity.ok("Profile do agente: " + authentication.getName());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Agente> buscarAgentePorId(@PathVariable UUID id) {
    try {
      Agente agente = agenteService.buscarAgentePorId(id);
      return ResponseEntity.ok(agente);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  public ResponseEntity<List<?>> listarTodosAgentes() {
    List<?> agentes = agenteService.listarTodosAgentes();
    return ResponseEntity.ok(agentes);
  }

  @PutMapping("/{id}/perfil")
  public ResponseEntity<Agente> atualizarPerfil(@PathVariable UUID id, @RequestParam String cnpj) {
    try {
      Agente agente = agenteService.atualizarPerfil(id, cnpj);
      return ResponseEntity.ok(agente);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/pedidos/{pedidoId}/avaliar")
  public ResponseEntity<String> avaliarPedido(@PathVariable UUID pedidoId, @RequestParam String decisao) {
    try {
      agenteService.avaliarPedido(pedidoId, decisao);
      return ResponseEntity.ok("Pedido avaliado com sucesso");
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/automoveis/{automovelId}/gerenciar")
  public ResponseEntity<String> gerenciarAutomoveis(@PathVariable UUID automovelId) {
    try {
      agenteService.gerenciarAutomoveis(automovelId);
      return ResponseEntity.ok("Automóvel gerenciado com sucesso");
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
