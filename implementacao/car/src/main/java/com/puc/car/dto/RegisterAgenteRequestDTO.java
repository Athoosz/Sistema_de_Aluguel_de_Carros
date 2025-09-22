package com.puc.car.dto;

import com.puc.car.models.enums.TipoAgente;

public record RegisterAgenteRequestDTO(
    String email, String senha, String cnpj, TipoAgente tipoAgente) {}
