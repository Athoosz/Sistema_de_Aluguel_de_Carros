package com.puc.car.models;

import java.util.List;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Usuario {

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String rg;

    private String nome;

    private String endereco;

    private String profissao;

    private Map<String, Double> rendaEntidade;

    private List<Pedido> pedidos;
}
