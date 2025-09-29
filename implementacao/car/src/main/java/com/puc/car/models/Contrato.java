package com.puc.car.models;

import com.puc.car.models.enums.TipoContrato;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Contrato {
    @Id
    private Long id;

    private TipoContrato tipoContrato;

    private double valorTotal;

}
