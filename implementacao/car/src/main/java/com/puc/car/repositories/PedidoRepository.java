package com.puc.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puc.car.models.Automovel;
import com.puc.car.models.Pedido;
import java.util.List;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByAutomovel(Automovel automovel);
}
