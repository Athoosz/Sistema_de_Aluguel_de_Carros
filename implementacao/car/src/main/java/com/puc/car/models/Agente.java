package com.puc.car.models;

import java.util.List;

import com.puc.car.models.enums.TipoAgente;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Agente extends Usuario {

    @Column(unique = true)
    private String cnpj;

    @Enumerated(EnumType.STRING)
    private TipoAgente tipoAgente;
    
    @OneToMany(mappedBy = "agente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> pedidos;
}
