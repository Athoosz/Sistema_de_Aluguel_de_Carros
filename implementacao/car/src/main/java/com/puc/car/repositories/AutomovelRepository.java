package com.puc.car.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.puc.car.models.Automovel;

public interface AutomovelRepository extends JpaRepository<Automovel, Long>{
    
    boolean existsByMatricula(String matricula);
    
    boolean existsByPlaca(String placa);
    
    List<Automovel> findByProprietarioId(Long proprietarioId);
    
    Optional<Automovel> findByMatricula(String matricula);
    
    Optional<Automovel> findByPlaca(String placa);
    
    @Query("SELECT a FROM Automovel a LEFT JOIN FETCH a.proprietario WHERE a.id = :id")
    Optional<Automovel> findByIdWithProprietario(@Param("id") Long id);
}
