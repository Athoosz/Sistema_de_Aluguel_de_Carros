package com.puc.car.dto.Automovel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record AutomovelRegisterRequest(
    
    @NotBlank(message = "Matrícula é obrigatória")
    @Size(max = 20, message = "Matrícula deve ter no máximo 20 caracteres")
    String matricula,
    
    @NotBlank(message = "Placa é obrigatória")
    @Pattern(regexp = "[A-Z]{3}[0-9]{4}|[A-Z]{3}[0-9][A-Z][0-9]{2}", message = "Placa deve estar no formato brasileiro")
    String placa,
    
    @NotNull(message = "Ano é obrigatório")
    LocalDate ano,
    
    @NotBlank(message = "Marca é obrigatória")
    @Size(max = 50, message = "Marca deve ter no máximo 50 caracteres")
    String marca,
    
    @NotBlank(message = "Modelo é obrigatório")
    @Size(max = 50, message = "Modelo deve ter no máximo 50 caracteres")
    String modelo,
    
    @NotNull(message = "ID do proprietário é obrigatório")
    Long proprietarioId
) {}