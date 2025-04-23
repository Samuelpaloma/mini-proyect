package com.samuel.crud_basic.DTO;

import java.time.LocalDateTime;

import com.samuel.crud_basic.model.Mesa;
import com.samuel.crud_basic.model.User;

public class ReservationDTO {
    
    private String name;
    private LocalDateTime fecha;
    private LocalDateTime hora;
    private int numeroPersonas;
    private int numeroCelular;
    private User user;
    private Mesa mesa;

    // Constructor vacío
    public ReservationDTO() {
    }

    // Constructor con parámetros
    public ReservationDTO(String name, LocalDateTime fecha, LocalDateTime hora, int numeroPersonas, int numeroCelular, User user, Mesa mesa) {
        this.name = name;
        this.fecha = fecha;
        this.hora = hora;
        this.numeroPersonas = numeroPersonas;
        this.numeroCelular = numeroCelular;
        this.user = user;
        this.mesa = mesa;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public int getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(int numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
}