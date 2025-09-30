package com.puc.car.dto.Pedido;

import com.puc.car.models.enums.EstadoPedido;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PedidoRequestRegister(

    @NotBlank
    String numPedido,

    @NotBlank
    @Email(message = "Email deve ter um formato válido")
    String emailCliente,

    @NotBlank
    @Email(message = "Email deve ter um formato válido")
    String emailAgente,
    
    @NotBlank
    EstadoPedido estadoPedido,

    @NotBlank
    Long automovelId,

    @NotBlank
    Long contratoId,

    @NotBlank
    double valor
) {}
