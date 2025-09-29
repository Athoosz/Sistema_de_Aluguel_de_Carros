package com.puc.car.models;

import java.util.List;
import java.util.Map;

import jakarta.persistence.*;
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

    @ElementCollection
    @CollectionTable(name = "cliente_renda", joinColumns = @JoinColumn(name = "cliente_id"))
    @MapKeyColumn(name = "fonte_renda")
    @Column(name = "valor")
    private Map<String, Double> rendaEntidade;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> pedidos;
}
