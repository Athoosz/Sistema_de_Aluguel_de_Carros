package com.puc.car.dto.Usuario;

public record LoginResponse(
    String token,
    String email,
    String role,
    Long userId
) {}