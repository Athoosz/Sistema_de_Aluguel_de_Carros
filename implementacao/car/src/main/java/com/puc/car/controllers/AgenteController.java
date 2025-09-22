package com.puc.car.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agente")
public class AgenteController {

  @GetMapping("/profile")
  public ResponseEntity<String> getProfile(Authentication authentication) {
    return ResponseEntity.ok("Profile do agente: " + authentication.getName());
  }
}
