package com.puc.car.dto.Usuario;

public record ClienteUpdateRequest(
    String nome,
    String endereco,
    String profissao,
    String cpf,
    String rg
) {}
