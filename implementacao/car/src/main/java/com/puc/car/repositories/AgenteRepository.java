package com.puc.car.repositories;

import com.puc.car.models.Agente;
import com.puc.car.models.enums.TipoAgente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgenteRepository extends JpaRepository<Agente, UUID> {
    
    Optional<Agente> findByCnpj(String cnpj);
    Optional<Agente> findByEmail(String email);
    List<Agente> findByTipoAgente(TipoAgente tipoAgente);
    boolean existsByCnpj(String cnpj);
}