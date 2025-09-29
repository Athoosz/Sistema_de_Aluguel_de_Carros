package com.puc.car.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public abstract class Usuario {
    @Id
    private Long id;

    @Column(unique = true)
    private String email;

    private String senha;
}
