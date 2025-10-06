package com.puc.car.controllers;

import com.puc.car.dto.Usuario.AgenteUpdateRequest;
import com.puc.car.dto.Usuario.ClienteUpdateRequest;
import com.puc.car.models.Agente;
import com.puc.car.models.Cliente;
import com.puc.car.models.Usuario;
import com.puc.car.models.enums.TipoAgente;
import com.puc.car.repositories.UsuarioRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

  @Autowired private UsuarioRepository usuarioRepository;

  @PutMapping("/profile/cliente")
  public ResponseEntity<?> updateClienteProfile(@RequestBody ClienteUpdateRequest updates) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    var usuario = usuarioRepository.findByEmail(email);
    if (!(usuario instanceof Cliente cliente)) {
      return ResponseEntity.status(403).body("Usuário autenticado não é um Cliente");
    }
    if (updates.nome() != null) cliente.setNome(updates.nome());
    if (updates.endereco() != null) cliente.setEndereco(updates.endereco());
    if (updates.profissao() != null) cliente.setProfissao(updates.profissao());
    if (updates.cpf() != null) cliente.setCpf(updates.cpf());
    if (updates.rg() != null) cliente.setRg(updates.rg());
    usuarioRepository.save(cliente);
    return ResponseEntity.ok("Perfil de Cliente atualizado com sucesso");
  }

  @PutMapping("/profile/agente")
  public ResponseEntity<?> updateAgenteProfile(@RequestBody AgenteUpdateRequest updates) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    var usuario = usuarioRepository.findByEmail(email);
    if (!(usuario instanceof Agente agente)) {
      return ResponseEntity.status(403).body("Usuário autenticado não é um Agente");
    }
    if (updates.cnpj() != null) agente.setCnpj(updates.cnpj());
    if (updates.tipoAgente() != null) {
      try {
        var tipo = TipoAgente.valueOf(updates.tipoAgente());
        agente.setTipoAgente(tipo);
      } catch (Exception ignored) {
      }
    }
    usuarioRepository.save(agente);
    return ResponseEntity.ok("Perfil de Agente atualizado com sucesso");
  }

  @DeleteMapping("/profile")
  public ResponseEntity<?> deleteUserProfile() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    Usuario usuario = usuarioRepository.findByEmail(email);
    if (usuario == null) {
      return ResponseEntity.status(404).body("Usuário não encontrado");
    }
    try {
      if (usuario instanceof Cliente cliente && cliente.getPedidos() != null) {
        cliente.getPedidos().clear();
      } else if (usuario instanceof Agente agente && agente.getPedidos() != null) {
        agente.getPedidos().clear();
      }
      usuarioRepository.delete(usuario);
      return ResponseEntity.ok("Usuário removido com sucesso");
    } catch (Exception ex) {
      return ResponseEntity.status(400)
          .body(
              "Não foi possível remover o usuário. Verifique se não há vínculos ativos (pedidos,"
                  + " contratos, etc). Erro: "
                  + ex.getMessage());
    }
  }

  @GetMapping("/profile")
  public ResponseEntity<?> getUserProfile() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Map<String, Object> response = new HashMap<>();
    response.put("username", authentication.getName());
    response.put("authorities", authentication.getAuthorities());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/test")
  public ResponseEntity<String> test() {
    return ResponseEntity.ok("Rota protegida funcionando! Usuário autenticado.");
  }
}
