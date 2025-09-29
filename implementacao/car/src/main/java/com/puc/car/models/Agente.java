package com.puc.car.models;

import com.puc.car.models.enums.TipoAgente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Agente extends Usuario {

    @Column(unique = true)
    private String cnpj;

    private TipoAgente tipoAgente;
}
