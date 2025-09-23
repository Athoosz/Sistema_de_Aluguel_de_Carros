package com.puc.car.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "automoveis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Automovel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @NotBlank(message = "Matrícula é obrigatória")
    @Column(unique = true)
    private String matricula;
    
    @NotNull(message = "Ano é obrigatório")
    private LocalDate ano;
    
    @NotBlank(message = "Marca é obrigatória")
    private String marca;
    
    @NotBlank(message = "Modelo é obrigatório")
    private String modelo;
    
    @NotBlank(message = "Placa é obrigatória")
    @Column(unique = true)
    private String placa;
    
    @NotBlank(message = "Proprietário é obrigatório")
    private String proprietario;
}