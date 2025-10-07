package com.puc.car.dto.Contrato;

import com.puc.car.models.enums.TipoContrato;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ContratoRegisterRequest(
    
    @NotNull(message = "Tipo de contrato é obrigatório")
    TipoContrato tipoContrato,
    
    @Positive(message = "Valor total deve ser positivo")
    double valorTotal,
    
    @NotNull(message = "ID do pedido é obrigatório")
    Long pedidoId
) {}