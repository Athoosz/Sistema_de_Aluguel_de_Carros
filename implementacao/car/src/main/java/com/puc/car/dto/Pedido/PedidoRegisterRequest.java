package com.puc.car.dto.Pedido;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PedidoRegisterRequest(

    @NotBlank(message = "Número do pedido é obrigatório")
    String numPedido,

    @NotBlank(message = "Email do cliente é obrigatório")
    @Email(message = "Email deve ter um formato válido")
    String emailCliente,

    @NotBlank(message = "Email do agente é obrigatório")
    @Email(message = "Email deve ter um formato válido")
    String emailAgente,

    @NotNull(message = "ID do automóvel é obrigatório")
    Long automovelId,

    @Positive(message = "Valor deve ser positivo")
    double valor
) {}
