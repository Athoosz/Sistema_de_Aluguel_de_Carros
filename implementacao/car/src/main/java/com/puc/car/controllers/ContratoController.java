package com.puc.car.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.puc.car.dto.Contrato.ContratoRegisterRequest;
import com.puc.car.models.Contrato;
import com.puc.car.models.enums.TipoContrato;
import com.puc.car.service.ContratoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/contratos")
@Tag(name = "Contratos", description = "Endpoints para gerenciamento de contratos")
@SecurityRequirement(name = "Bearer Authentication")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @PostMapping
    @Operation(summary = "Criar novo contrato", description = "Cria um novo contrato para um pedido")
    public ResponseEntity<Contrato> criarContrato(@Valid @RequestBody ContratoRegisterRequest request) {
        Contrato contrato = contratoService.criarContrato(request);
        return ResponseEntity.ok(contrato);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar contrato por ID", description = "Busca um contrato específico pelo ID")
    public ResponseEntity<Contrato> buscarPorId(@PathVariable Long id) {
        Contrato contrato = contratoService.buscarPorId(id);
        return ResponseEntity.ok(contrato);
    }

    @GetMapping
    @Operation(summary = "Listar todos os contratos", description = "Retorna uma lista de todos os contratos")
    public ResponseEntity<List<Contrato>> listarTodos() {
        List<Contrato> contratos = contratoService.listarTodos();
        return ResponseEntity.ok(contratos);
    }

    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Buscar contratos por cliente", description = "Busca todos os contratos de um cliente específico")
    public ResponseEntity<List<Contrato>> buscarPorCliente(@PathVariable Long clienteId) {
        List<Contrato> contratos = contratoService.buscarPorCliente(clienteId);
        return ResponseEntity.ok(contratos);
    }

    @GetMapping("/agente/{agenteId}")
    @Operation(summary = "Buscar contratos por agente", description = "Busca todos os contratos de um agente específico")
    public ResponseEntity<List<Contrato>> buscarPorAgente(@PathVariable Long agenteId) {
        List<Contrato> contratos = contratoService.buscarPorAgente(agenteId);
        return ResponseEntity.ok(contratos);
    }

    @GetMapping("/tipo/{tipoContrato}")
    @Operation(summary = "Buscar contratos por tipo", description = "Busca contratos de um tipo específico")
    public ResponseEntity<List<Contrato>> buscarPorTipo(@PathVariable TipoContrato tipoContrato) {
        List<Contrato> contratos = contratoService.buscarPorTipo(tipoContrato);
        return ResponseEntity.ok(contratos);
    }

    @GetMapping("/pedido/{pedidoId}")
    @Operation(summary = "Buscar contrato por pedido", description = "Busca o contrato associado a um pedido específico")
    public ResponseEntity<Contrato> buscarPorPedido(@PathVariable Long pedidoId) {
        Contrato contrato = contratoService.buscarPorPedido(pedidoId);
        return ResponseEntity.ok(contrato);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar contrato", description = "Atualiza os dados de um contrato existente")
    public ResponseEntity<Contrato> atualizarContrato(@PathVariable Long id, 
                                                     @Valid @RequestBody ContratoRegisterRequest request) {
        Contrato contrato = contratoService.atualizarContrato(id, request);
        return ResponseEntity.ok(contrato);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar contrato", description = "Remove um contrato do sistema")
    public ResponseEntity<Void> deletarContrato(@PathVariable Long id) {
        contratoService.deletarContrato(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/aprovar")
    @Operation(summary = "Aprovar contrato", description = "Aprova um contrato específico")
    public ResponseEntity<Void> aprovarContrato(@PathVariable Long id) {
        contratoService.aprovarContrato(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/rejeitar")
    @Operation(summary = "Rejeitar contrato", description = "Rejeita um contrato específico")
    public ResponseEntity<Void> rejeitarContrato(@PathVariable Long id) {
        contratoService.rejeitarContrato(id);
        return ResponseEntity.ok().build();
    }
}