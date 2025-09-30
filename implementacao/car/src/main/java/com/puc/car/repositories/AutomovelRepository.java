package com.puc.car.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puc.car.models.Automovel;

public interface AutomovelRepository extends JpaRepository<Automovel, Long>{
    
    boolean existsByMatricula(String matricula);
    
    boolean existsByPlaca(String placa);
    
    List<Automovel> findByProprietarioId(Long proprietarioId);
    
    Optional<Automovel> findByMatricula(String matricula);
    
    Optional<Automovel> findByPlaca(String placa);
}
