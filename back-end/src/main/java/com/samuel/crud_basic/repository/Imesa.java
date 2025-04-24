package com.samuel.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuel.crud_basic.model.Mesa;

public interface Imesa extends JpaRepository<Mesa, Integer> {

    // Método para obtener todas las mesas disponibles (no ocupadas)
    List<Mesa> findByOcupadaFalse();
}