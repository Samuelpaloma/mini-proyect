package com.samuel.crud_basic.DTO;

public class MesaDTO {
    private int idMesa; // Identificador de la mesa
    private int capacidad; // Capacidad de la mesa
    private String ubicacion; // Ubicación de la mesa
    private boolean ocupada; // Estado de la mesa (true = ocupada, false = disponible)

    // Constructor vacío
    public MesaDTO() {}

    // Constructor con todos los parámetros
    public MesaDTO(int idMesa, int capacidad, String ubicacion, boolean ocupada) {
        this.idMesa = idMesa;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.ocupada = ocupada;
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

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
}