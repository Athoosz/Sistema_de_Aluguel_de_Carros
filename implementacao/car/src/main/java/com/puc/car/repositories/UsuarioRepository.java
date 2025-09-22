package com.puc.car.repositories;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.puc.car.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
  Optional<Usuario> findByEmail(String email);
}
