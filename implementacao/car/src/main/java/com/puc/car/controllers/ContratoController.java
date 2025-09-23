package com.puc.car.controllers;

import com.puc.car.models.Contrato;
import com.puc.car.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @PostMapping
    public ResponseEntity<Contrato> criarContrato(@RequestBody Contrato contrato) {
        try {
            Contrato novoContrato = contratoService.criarContrato(contrato);
            return ResponseEntity.ok(novoContrato);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Contrato>> listarContratos() {
        List<Contrato> contratos = contratoService.listarTodos();
        return ResponseEntity.ok(contratos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrato> buscarContratoPorId(@PathVariable UUID id) {
        try {
            Contrato contrato = contratoService.buscarPorId(id);
            return ResponseEntity.ok(contrato);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Contrato>> buscarPorClienteId(@PathVariable UUID clienteId) {
        List<Contrato> contratos = contratoService.buscarPorClienteId(clienteId);
        return ResponseEntity.ok(contratos);
    }

    @GetMapping("/automovel/{automovelId}")
    public ResponseEntity<List<Contrato>> buscarPorAutomovelId(@PathVariable UUID automovelId) {
        List<Contrato> contratos = contratoService.buscarPorAutomovelId(automovelId);
        return ResponseEntity.ok(contratos);
    }

    @PatchMapping("/{id}/alteracao")
    public ResponseEntity<Contrato> alterarContrato(
            @PathVariable UUID id, 
            @RequestBody Contrato dadosAlteracao) {
        try {
            Contrato contratoAlterado = contratoService.alterarContrato(id, dadosAlteracao);
            return ResponseEntity.ok(contratoAlterado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrato> atualizarContrato(
            @PathVariable UUID id, 
            @RequestBody Contrato contrato) {
        try {
            Contrato contratoAtualizado = contratoService.atualizarContrato(id, contrato);
            return ResponseEntity.ok(contratoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerContrato(@PathVariable UUID id) {
        try {
            contratoService.removerContrato(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}