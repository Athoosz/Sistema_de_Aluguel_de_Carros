package com.puc.car.dto;

public record LoginResponse(
    String token,
    String email,
    String role,
    Long userId
) {}