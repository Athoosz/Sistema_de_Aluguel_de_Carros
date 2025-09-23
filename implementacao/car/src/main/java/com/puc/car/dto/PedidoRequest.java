package com.puc.car.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {
    
    @NotNull(message = "Cliente é obrigatório")
    private UUID clienteId;
    
    @NotNull(message = "Automóvel é obrigatório")
    private UUID automovelId;
    
    @NotNull(message = "Valor total é obrigatório")
    @Positive(message = "Valor total deve ser positivo")
    private Double valorTotal;
}