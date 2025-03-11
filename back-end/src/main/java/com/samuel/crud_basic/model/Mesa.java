package com.samuel.crud_basic.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "mesa")  // Se cambió "table" a "mesa"
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mesa")  // Se cambió "id_table" a "id_mesa"
    private int idMesa;

    @Column(name = "capacidad", nullable = false)
    private int capacidad;

    @Column(name = "ubicacion", length = 150, nullable = false)
    private String ubicacion;

    @OneToMany(mappedBy = "mesa") // Relación con reservas
    private List<Reservation> reservas;

    // Constructor vacío requerido por JPA
    public Mesa() {
    }

    // Constructor con parámetros
    public Mesa(int idMesa, int capacidad, String ubicacion) {
        this.idMesa = idMesa;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
    }

    // Getters y Setters
    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Reservation> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reservation> reservas) {
        this.reservas = reservas;
    }
}
