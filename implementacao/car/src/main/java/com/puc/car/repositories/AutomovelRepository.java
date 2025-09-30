package com.puc.car.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puc.car.models.Automovel;

public interface AutomovelRepository extends JpaRepository<Automovel, Long>{

}
