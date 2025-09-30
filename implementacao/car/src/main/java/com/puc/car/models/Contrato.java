package com.puc.car.models;

import com.puc.car.models.enums.TipoContrato;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoContrato tipoContrato;

    private double valorTotal;
    
    private LocalDateTime dataCriacao;
    
    private boolean aprovado = false;
    
    private LocalDateTime dataAprovacao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
