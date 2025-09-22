package com.puc.car.infra.security;

import com.puc.car.models.Agente;
import com.puc.car.models.Cliente;
import com.puc.car.models.Usuario;
import com.puc.car.repositories.UsuarioRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired private UsuarioRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario user =
        this.repository
            .findByEmail(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("User not found with email: " + username));

    // Determinar o role baseado no tipo da entidade
    String role;
    if (user instanceof Cliente) {
      role = "CLIENTE";
    } else if (user instanceof Agente) {
      role = "AGENTE";
    } else {
      role = "USUARIO"; // default
    }

    return new org.springframework.security.core.userdetails.User(
        user.getEmail(),
        user.getSenha(),
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role)));
  }
}
