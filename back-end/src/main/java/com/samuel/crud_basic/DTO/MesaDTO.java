package com.samuel.crud_basic.DTO;

public class MesaDTO {
    private int capacidad;

    private String ubicacion;

    public MesaDTO(){}

    public MesaDTO(int capacidad, String ubicacion) {
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
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

    
}
