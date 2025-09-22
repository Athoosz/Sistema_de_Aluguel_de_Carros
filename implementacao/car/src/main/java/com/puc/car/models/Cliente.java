package com.puc.car.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "clientes")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Usuario {
    
    @Column(unique = true, nullable = false)
    private String cpf;
    
    @Column(nullable = false)
    private String rg;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String endereco;
    
    private String profissao;
    
    @ElementCollection
    @CollectionTable(name = "cliente_renda_entidade", joinColumns = @JoinColumn(name = "cliente_id"))
    @MapKeyColumn(name = "entidade")
    @Column(name = "renda")
    private Map<String, Double> rendaEntidade = new HashMap<>();

}