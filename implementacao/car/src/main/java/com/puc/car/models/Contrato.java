package com.puc.car.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.puc.car.models.enums.TipoContrato;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

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

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pedido_id")
  @JsonIgnore
  private Pedido pedido;
}
