package com.samuel.crud_basic.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private int idReservation;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "numero_personas", nullable = false)
    private int numeroPersonas;

    @Column(name = "numero_celular", nullable = false)
    private int numeroCelular;

    @Column(name="status",nullable=false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "id_mesa", nullable = false)
    @JsonManagedReference
    private Mesa mesa;

    // Constructor vacío (requerido por JPA)
    public Reservation() {
    }

    // Constructor con parámetros
    public Reservation(int idReservation, String name, LocalDateTime fecha, int numeroPersonas, int numeroCelular, Mesa mesa, boolean status) {
        this.idReservation = idReservation;
        this.name = name;
        this.fecha = fecha;
        this.numeroPersonas = numeroPersonas;
        this.numeroCelular = numeroCelular;
        this.mesa = mesa;
        this.status = status;
    }

    // Getters y Setters
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

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

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}