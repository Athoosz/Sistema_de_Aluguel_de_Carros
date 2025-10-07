package com.puc.car.dto.Automovel;

import java.time.LocalDate;

public record AutomovelResponse(
    Long id,
    String matricula,
    String placa,
    LocalDate ano,
    String marca,
    String modelo,
    Long proprietarioId
) {
    public static AutomovelResponse fromEntity(com.puc.car.models.Automovel automovel) {
        return new AutomovelResponse(
            automovel.getId(),
            automovel.getMatricula(),
            automovel.getPlaca(),
            automovel.getAno(),
            automovel.getMarca(),
            automovel.getModelo(),
            automovel.getProprietario() != null ? automovel.getProprietario().getId() : null
        );
    }
}
