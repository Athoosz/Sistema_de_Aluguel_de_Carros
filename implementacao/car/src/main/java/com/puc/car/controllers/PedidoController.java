package com.puc.car.controllers;

import com.puc.car.dto.Pedido.PedidoRegisterRequest;
import com.puc.car.models.Pedido;
import com.puc.car.service.PedidoService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*")
public class PedidoController {

  @PatchMapping("/{id}/aceitar")
  public ResponseEntity<?> aceitarPedido(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @PathVariable Long id) {
    try {
      if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
        return ResponseEntity.status(401).body("Token não informado");
      }
      String token = authorizationHeader.replace("Bearer ", "");
      Pedido pedido = pedidoService.aceitarPedido(id, token);
      return ResponseEntity.ok(pedido);
    } catch (Exception e) {
      Map<String, String> error = new HashMap<>();
      error.put("error", e.getMessage());
      return ResponseEntity.badRequest().body(error);
    }
  }

  @PatchMapping("/{id}/rejeitar")
  public ResponseEntity<?> rejeitarPedido(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @PathVariable Long id) {
    try {
      if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
        return ResponseEntity.status(401).body("Token não informado");
      }
      String token = authorizationHeader.replace("Bearer ", "");
      Pedido pedido = pedidoService.rejeitarPedido(id, token);
      return ResponseEntity.ok(pedido);
    } catch (Exception e) {
      Map<String, String> error = new HashMap<>();
      error.put("error", e.getMessage());
      return ResponseEntity.badRequest().body(error);
    }
  }

  @Autowired private PedidoService pedidoService;

  @PostMapping("/create")
  public ResponseEntity<?> createPedido(
      @RequestBody @Valid PedidoRegisterRequest pedidoRegisterRequest) {
    try {
      Pedido pedido = pedidoService.gerarPedido(pedidoRegisterRequest);
      return ResponseEntity.ok(pedido);
    } catch (Exception e) {
      Map<String, String> error = new HashMap<>();
      error.put("error", e.getMessage());
      return ResponseEntity.badRequest().body(error);
    }
  }

  @GetMapping("/{numPedido}")
  public ResponseEntity<?> getPedido(@PathVariable String numPedido) {
    try {
      Pedido pedido = pedidoService.getPedido(numPedido);
      return ResponseEntity.ok(pedido);
    } catch (Exception e) {
      Map<String, String> error = new HashMap<>();
      error.put("error", e.getMessage());
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletePedido(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @PathVariable Long id) {
    try {
      if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
        return ResponseEntity.status(401).body("Token não informado");
      }
      String token = authorizationHeader.replace("Bearer ", "");
      pedidoService.deletePedido(id, token);
      Map<String, String> response = new HashMap<>();
      response.put("message", "Pedido deletado com sucesso");
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      Map<String, String> error = new HashMap<>();
      error.put("error", e.getMessage());
      return ResponseEntity.badRequest().body(error);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> atualizarPedido(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
      @PathVariable Long id,
      @RequestBody @Valid PedidoRegisterRequest pedidoRegisterRequest) {
    try {
      if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
        return ResponseEntity.status(401).body("Token não informado");
      }
      String token = authorizationHeader.replace("Bearer ", "");
      Pedido pedido = pedidoService.atualizarPedido(id, pedidoRegisterRequest, token);
      return ResponseEntity.ok(pedido);
    } catch (Exception e) {
      Map<String, String> error = new HashMap<>();
      error.put("error", e.getMessage());
      return ResponseEntity.badRequest().body(error);
    }
  }
}
