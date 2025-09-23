package com.puc.car.dto;

import com.puc.car.models.enums.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponse {
    private UUID id;
    private UUID clienteId;
    private UUID automovelId;
    private EstadoPedido estadoPedido;
    private Double valorTotal;
}