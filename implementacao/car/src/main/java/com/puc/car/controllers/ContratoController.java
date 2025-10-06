package com.puc.car.controllers;

import com.puc.car.dto.Contrato.ContratoRegisterRequest;
import com.puc.car.models.Contrato;
import com.puc.car.service.ContratoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

  @Autowired private ContratoService contratoService;

  @PostMapping
  public ResponseEntity<?> criarContrato(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
      @Valid @RequestBody ContratoRegisterRequest request) {
    try {
      if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token não informado");
      }
      String token = authorizationHeader.replace("Bearer ", "");
      Contrato contrato = contratoService.criarContrato(request, token);
      return ResponseEntity.ok(contrato);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Erro ao criar contrato: " + e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Contrato> buscarPorId(@PathVariable Long id) {
    Contrato contrato = contratoService.buscarPorId(id);
    return ResponseEntity.ok(contrato);
  }

  @GetMapping("/pedido/{pedidoId}")
  public ResponseEntity<Contrato> buscarPorPedido(@PathVariable Long pedidoId) {
    Contrato contrato = contratoService.buscarPorPedido(pedidoId);
    return ResponseEntity.ok(contrato);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Contrato> atualizarContrato(
      @PathVariable Long id, @Valid @RequestBody ContratoRegisterRequest request) {
    Contrato contrato = contratoService.atualizarContrato(id, request);
    return ResponseEntity.ok(contrato);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarContrato(@PathVariable Long id) {
    contratoService.deletarContrato(id);
    return ResponseEntity.noContent().build();
  }
}
