package com.puc.car.dto;

import com.puc.car.models.enums.TipoContrato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratoResponse {
    private UUID id;
    private UUID clienteId;
    private UUID agenteId;
    private UUID automovelId;
    private UUID pedidoId;
    private TipoContrato tipoContrato;
    private Double valor;
}