package com.puc.car.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.puc.car.dto.Automovel.AutomovelRegisterRequest;
import com.puc.car.dto.Automovel.AutomovelResponse;
import com.puc.car.models.Automovel;
import com.puc.car.service.AutomovelService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/automoveis")
public class AutomovelController {

    @Autowired
    private AutomovelService automovelService;

    @PostMapping("/criar")
    public ResponseEntity<AutomovelResponse> criarAutomovel(@Valid @RequestBody AutomovelRegisterRequest request) {
        Automovel automovel = automovelService.criarAutomovel(request);
        return ResponseEntity.ok(AutomovelResponse.fromEntity(automovel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutomovelResponse> buscarPorId(@PathVariable Long id) {
        Automovel automovel = automovelService.buscarPorId(id);
        return ResponseEntity.ok(AutomovelResponse.fromEntity(automovel));
    }

    @GetMapping("/proprietario/{proprietarioId}")
    public ResponseEntity<List<AutomovelResponse>> buscarPorProprietario(@PathVariable Long proprietarioId) {
        List<Automovel> automoveis = automovelService.buscarPorProprietario(proprietarioId);
        List<AutomovelResponse> response = automoveis.stream()
            .map(AutomovelResponse::fromEntity)
            .toList();
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AutomovelResponse> atualizarAutomovel(@PathVariable Long id, 
                                                       @Valid @RequestBody AutomovelRegisterRequest request) {
        Automovel automovel = automovelService.atualizarAutomovel(id, request);
        return ResponseEntity.ok(AutomovelResponse.fromEntity(automovel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutomovel(@PathVariable Long id) {
        automovelService.deletarAutomovel(id);
        return ResponseEntity.noContent().build();
    }
}