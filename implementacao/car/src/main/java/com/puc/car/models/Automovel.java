package com.puc.car.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Automovel {
    @Id
    private Long id;
    
    private String matricula;
    
    private String placa;

    private LocalDate ano;

    private String marca;

    private String modelo;

    private Usuario proprietario;
}
