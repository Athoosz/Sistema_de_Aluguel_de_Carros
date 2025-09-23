package com.puc.car.dto;

import com.puc.car.models.enums.TipoContrato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratoRequestDTO {
    
    @NotNull(message = "Cliente é obrigatório")
    private UUID clienteId;
    
    @NotNull(message = "Agente é obrigatório")
    private UUID agenteId;
    
    @NotNull(message = "Automóvel é obrigatório")
    private UUID automovelId;
    
    @NotNull(message = "Pedido é obrigatório")
    private UUID pedidoId;
    
    @NotNull(message = "Tipo de contrato é obrigatório")
    private TipoContrato tipoContrato;
    
    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser positivo")
    private Double valor;
}