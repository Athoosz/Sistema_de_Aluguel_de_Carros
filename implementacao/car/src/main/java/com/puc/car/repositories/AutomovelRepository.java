package com.puc.car.repositories;

import com.puc.car.models.Automovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, UUID> {
    
    Optional<Automovel> findByMatricula(String matricula);
    Optional<Automovel> findByPlaca(String placa);
    List<Automovel> findByMarca(String marca);
    List<Automovel> findByModelo(String modelo);
    List<Automovel> findByProprietario(String proprietario);
    List<Automovel> findByMarcaAndModelo(String marca, String modelo);
    boolean existsByMatricula(String matricula);
    boolean existsByPlaca(String placa);
}