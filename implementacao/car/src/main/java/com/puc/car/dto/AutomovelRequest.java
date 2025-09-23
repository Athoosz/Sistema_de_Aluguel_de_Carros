package com.puc.car.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutomovelRequest {
    
    @NotBlank(message = "Matrícula é obrigatória")
    private String matricula;
    
    @NotBlank(message = "Placa é obrigatória")
    private String placa;
    
    @NotBlank(message = "Marca é obrigatória")
    private String marca;
    
    @NotBlank(message = "Modelo é obrigatório")
    private String modelo;
    
    @NotNull(message = "Ano é obrigatório")
    @Positive(message = "Ano deve ser positivo")
    private Integer ano;
    
    @NotBlank(message = "Proprietário é obrigatório")
    private String proprietario;
}