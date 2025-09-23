package com.puc.car.dto;

public record RegisterClienteRequest(
    String email,
    String senha,
    String cpf,
    String rg,
    String nome,
    String endereco,
    String profissao) {}
