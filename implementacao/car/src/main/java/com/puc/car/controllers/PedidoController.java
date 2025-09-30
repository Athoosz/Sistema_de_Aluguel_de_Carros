package com.puc.car.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.puc.car.dto.Pedido.PedidoRegisterRequest;

import com.puc.car.models.Pedido;
import com.puc.car.service.PedidoService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/create")
    public ResponseEntity<?> createPedido(@RequestBody @Valid PedidoRegisterRequest pedidoRegisterRequest){
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
    public ResponseEntity<?> getPedido(@PathVariable String numPedido){
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
    public ResponseEntity<?> deletePedido(@PathVariable Long id){
        try {
            pedidoService.deletePedido(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Pedido deletado com sucesso");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
