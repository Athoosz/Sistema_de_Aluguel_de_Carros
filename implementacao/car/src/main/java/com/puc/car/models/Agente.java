package com.puc.car.models;

import com.puc.car.models.enums.TipoAgente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agentes")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Agente extends Usuario {

  @Column(unique = true, nullable = false)
  private String cnpj;

  @Column(nullable = false)
  private TipoAgente tipoAgente;
}
