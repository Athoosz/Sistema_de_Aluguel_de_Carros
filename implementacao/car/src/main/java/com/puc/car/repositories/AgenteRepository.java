package com.puc.car.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puc.car.models.Agente;

public interface AgenteRepository extends JpaRepository<Agente, Long>{
    Optional<Agente> findByEmail(String email);
}
