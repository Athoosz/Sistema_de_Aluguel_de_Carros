package com.puc.car.dto.Pedido;

import com.puc.car.models.enums.EstadoPedido;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PedidoRegisterRequest(
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ter um formato válido")
    String emailCliente,

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ter um formato válido")
    String emailAgente,
    
    @NotBlank
    EstadoPedido estadoPedido,

    @NotBlank
    Long automovelId,

    @NotBlank
    Long contratoId
) {}
