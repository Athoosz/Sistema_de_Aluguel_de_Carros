package com.puc.car.dto.Usuariodto;

public record LoginResponse(
    String token,
    String email,
    String role,
    Long userId
) {}