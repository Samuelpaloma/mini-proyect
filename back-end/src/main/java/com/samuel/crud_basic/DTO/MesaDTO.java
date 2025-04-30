package com.samuel.crud_basic.DTO;

public class MesaDTO {

    private int capacidad;
    private String ubicacion;
    private boolean ocupada;

    // Constructor vacío
    public MesaDTO() {
    }

    // Constructor con parámetros
    public MesaDTO(int capacidad, String ubicacion, boolean ocupada) {
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.ocupada = ocupada;
    }

    // Getters y setters
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
}