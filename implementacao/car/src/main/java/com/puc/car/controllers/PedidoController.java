package com.puc.car.controllers;

import com.puc.car.models.Pedido;
import com.puc.car.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        try {
            Pedido novoPedido = pedidoService.criarPedido(pedido);
            return ResponseEntity.ok(novoPedido);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarTodos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable UUID id) {
        try {
            Pedido pedido = pedidoService.buscarPorId(id);
            return ResponseEntity.ok(pedido);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> buscarPorClienteId(@PathVariable UUID clienteId) {
        List<Pedido> pedidos = pedidoService.buscarPorClienteId(clienteId);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/agente/{agenteId}")
    public ResponseEntity<List<Pedido>> buscarPorAgenteId(@PathVariable UUID agenteId) {
        List<Pedido> pedidos = pedidoService.buscarPorAgenteId(agenteId);
        return ResponseEntity.ok(pedidos);
    }

    @PatchMapping("/{id}/aprovar")
    public ResponseEntity<Pedido> aprovarPedido(@PathVariable UUID id) {
        try {
            Pedido pedidoAprovado = pedidoService.aprovarPedido(id);
            return ResponseEntity.ok(pedidoAprovado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Pedido> cancelarPedido(@PathVariable UUID id) {
        try {
            Pedido pedidoCancelado = pedidoService.cancelarPedido(id);
            return ResponseEntity.ok(pedidoCancelado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(
            @PathVariable UUID id, 
            @RequestBody Pedido pedido) {
        try {
            Pedido pedidoAtualizado = pedidoService.atualizarPedido(id, pedido);
            return ResponseEntity.ok(pedidoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPedido(@PathVariable UUID id) {
        try {
            pedidoService.removerPedido(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}