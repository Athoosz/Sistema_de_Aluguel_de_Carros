package com.puc.car.models;

import com.puc.car.models.enums.EstadoPedido;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Pedido {

    @Id
    private Long id;

    private Cliente cliente;

    private Agente agente;

    private EstadoPedido estadoPedido;

    private Automovel automovel;

    private Contrato contrato;
}
