package com.samuel.crud_basic.DTO;

import java.time.LocalDateTime;

public class ReservationDTO {

    private String name;
    private LocalDateTime fecha;
    private int numeroPersonas;
    private int numeroCelular;
    private int idMesa; // ID de la mesa seleccionada

    // Constructor vacío
    public ReservationDTO() {
    }

    // Constructor con parámetros
    public ReservationDTO(String name, LocalDateTime fecha, int numeroPersonas, int numeroCelular, int idMesa) {
        this.name = name;
        this.fecha = fecha;
        this.numeroPersonas = numeroPersonas;
        this.numeroCelular = numeroCelular;
        this.idMesa = idMesa;
    }

    // Getters y setters
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

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }
}