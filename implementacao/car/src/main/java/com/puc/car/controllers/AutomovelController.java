package com.puc.car.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.puc.car.dto.Automovel.AutomovelRegisterRequest;
import com.puc.car.models.Automovel;
import com.puc.car.service.AutomovelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/automoveis")
@Tag(name = "Automóveis", description = "Endpoints para gerenciamento de automóveis")
@SecurityRequirement(name = "Bearer Authentication")
public class AutomovelController {

    @Autowired
    private AutomovelService automovelService;

    @PostMapping
    @Operation(summary = "Criar novo automóvel", description = "Cadastra um novo automóvel no sistema")
    public ResponseEntity<Automovel> criarAutomovel(@Valid @RequestBody AutomovelRegisterRequest request) {
        Automovel automovel = automovelService.criarAutomovel(request);
        return ResponseEntity.ok(automovel);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar automóvel por ID", description = "Busca um automóvel específico pelo ID")
    public ResponseEntity<Automovel> buscarPorId(@PathVariable Long id) {
        Automovel automovel = automovelService.buscarPorId(id);
        return ResponseEntity.ok(automovel);
    }

    @GetMapping("/proprietario/{proprietarioId}")
    @Operation(summary = "Buscar automóveis por proprietário", description = "Busca todos os automóveis de um proprietário específico")
    public ResponseEntity<List<Automovel>> buscarPorProprietario(@PathVariable Long proprietarioId) {
        List<Automovel> automoveis = automovelService.buscarPorProprietario(proprietarioId);
        return ResponseEntity.ok(automoveis);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar automóvel", description = "Atualiza os dados de um automóvel existente")
    public ResponseEntity<Automovel> atualizarAutomovel(@PathVariable Long id, 
                                                       @Valid @RequestBody AutomovelRegisterRequest request) {
        Automovel automovel = automovelService.atualizarAutomovel(id, request);
        return ResponseEntity.ok(automovel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar automóvel", description = "Remove um automóvel do sistema")
    public ResponseEntity<Void> deletarAutomovel(@PathVariable Long id) {
        automovelService.deletarAutomovel(id);
        return ResponseEntity.noContent().build();
    }
}