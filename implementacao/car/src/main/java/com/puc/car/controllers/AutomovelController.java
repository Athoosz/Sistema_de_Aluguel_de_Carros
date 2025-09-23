package com.puc.car.controllers;

import com.puc.car.models.Automovel;
import com.puc.car.service.AutomovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/automoveis")
public class AutomovelController {

    @Autowired
    private AutomovelService automovelService;

    @PostMapping
    public ResponseEntity<Automovel> criarAutomovel(@RequestBody Automovel automovel) {
        try {
            Automovel novoAutomovel = automovelService.criarAutomovel(automovel);
            return ResponseEntity.ok(novoAutomovel);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Automovel>> listarAutomoveis() {
        List<Automovel> automoveis = automovelService.listarTodos();
        return ResponseEntity.ok(automoveis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Automovel> buscarAutomovelPorId(@PathVariable UUID id) {
        try {
            Automovel automovel = automovelService.buscarPorId(id);
            return ResponseEntity.ok(automovel);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Automovel> buscarPorMatricula(@PathVariable String matricula) {
        try {
            Automovel automovel = automovelService.buscarPorMatricula(matricula);
            return ResponseEntity.ok(automovel);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<Automovel> buscarPorPlaca(@PathVariable String placa) {
        try {
            Automovel automovel = automovelService.buscarPorPlaca(placa);
            return ResponseEntity.ok(automovel);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Automovel>> buscarPorMarca(@PathVariable String marca) {
        List<Automovel> automoveis = automovelService.buscarPorMarca(marca);
        return ResponseEntity.ok(automoveis);
    }

    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<List<Automovel>> buscarPorModelo(@PathVariable String modelo) {
        List<Automovel> automoveis = automovelService.buscarPorModelo(modelo);
        return ResponseEntity.ok(automoveis);
    }

    @GetMapping("/proprietario/{proprietario}")
    public ResponseEntity<List<Automovel>> buscarPorProprietario(@PathVariable String proprietario) {
        List<Automovel> automoveis = automovelService.buscarPorProprietario(proprietario);
        return ResponseEntity.ok(automoveis);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Automovel>> buscarPorMarcaEModelo(
            @RequestParam String marca, 
            @RequestParam String modelo) {
        List<Automovel> automoveis = automovelService.buscarPorMarcaEModelo(marca, modelo);
        return ResponseEntity.ok(automoveis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Automovel> atualizarAutomovel(
            @PathVariable UUID id, 
            @RequestBody Automovel automovel) {
        try {
            Automovel automovelAtualizado = automovelService.atualizarAutomovel(id, automovel);
            return ResponseEntity.ok(automovelAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAutomovel(@PathVariable UUID id) {
        try {
            automovelService.removerAutomovel(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}