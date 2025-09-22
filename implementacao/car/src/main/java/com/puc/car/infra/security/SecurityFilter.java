package com.puc.car.infra.security;

import com.puc.car.models.Agente;
import com.puc.car.models.Cliente;
import com.puc.car.models.Usuario;
import com.puc.car.repositories.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SecurityFilter extends OncePerRequestFilter {
  @Autowired TokenService tokenService;
  @Autowired UsuarioRepository userRepository;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    var token = this.recoverToken(request);
    var login = tokenService.validateToken(token);

    if (login != null) {
      Usuario user =
          userRepository
              .findByEmail(login)
              .orElseThrow(() -> new RuntimeException("User Not Found"));

      // Determinar o role baseado no tipo da entidade
      String role;
      if (user instanceof Cliente) {
        role = "CLIENTE";
      } else if (user instanceof Agente) {
        role = "AGENTE";
      } else {
        role = "USUARIO"; // default
      }

      var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
      var authentication =
          new UsernamePasswordAuthenticationToken(user.getEmail(), null, authorities);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");
    if (authHeader == null) return null;
    return authHeader.replace("Bearer ", "");
  }
}
