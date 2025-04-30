package com.samuel.crud_basic.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMesa; // Identificador único de la mesa

    @Column(nullable = false)
    private int capacidad; // Capacidad de la mesa

    @Column(nullable = false)
    private String ubicacion; // Ubicación de la mesa

    @Column(nullable = false)
    private boolean ocupada = false; // Estado de ocupación de la mesa

    @OneToMany(mappedBy = "mesa") 
    @JsonBackReference
    private List<Reservation> reservas;


    // Constructor vacío requerido por JPA
    public Mesa() {
    }


    public Mesa(int idMesa,int capacidad, String ubicacion, boolean ocupada, List<Reservation> reservas) {
        this.idMesa = idMesa;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.ocupada = ocupada;
        this.reservas = reservas;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getIdMesa(){
        return idMesa;
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


    public boolean isOcupada() {
        return ocupada;
    }


    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }


    public List<Reservation> getReservas() {
        return reservas;
    }


    public void setReservas(List<Reservation> reservas) {
        this.reservas = reservas;
    }
}
