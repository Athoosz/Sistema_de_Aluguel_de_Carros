package com.puc.car.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.puc.car.dto.Contrato.ContratoRegisterRequest;
import com.puc.car.models.Contrato;
import com.puc.car.service.ContratoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @PostMapping
    public ResponseEntity<Contrato> criarContrato(@Valid @RequestBody ContratoRegisterRequest request) {
        Contrato contrato = contratoService.criarContrato(request);
        return ResponseEntity.ok(contrato);
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
    public ResponseEntity<Contrato> atualizarContrato(@PathVariable Long id, 
                                                     @Valid @RequestBody ContratoRegisterRequest request) {
        Contrato contrato = contratoService.atualizarContrato(id, request);
        return ResponseEntity.ok(contrato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContrato(@PathVariable Long id) {
        contratoService.deletarContrato(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/aprovar")
    public ResponseEntity<Void> aprovarContrato(@PathVariable Long id) {
        contratoService.aprovarContrato(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/rejeitar")
    public ResponseEntity<Void> rejeitarContrato(@PathVariable Long id) {
        contratoService.rejeitarContrato(id);
        return ResponseEntity.ok().build();
    }
}