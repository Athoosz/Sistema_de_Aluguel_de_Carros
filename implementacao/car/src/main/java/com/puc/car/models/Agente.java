package com.puc.car.models;

import com.puc.car.models.enums.TipoAgente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Agente extends Usuario {

    @Column(unique = true)
    private String cnpj;

    private TipoAgente tipoAgente;
}
