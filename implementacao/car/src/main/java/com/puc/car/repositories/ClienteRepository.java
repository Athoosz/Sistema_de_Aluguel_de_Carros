package com.puc.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puc.car.models.Cliente;
import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    Optional<Cliente> findByEmail(String email);
}
