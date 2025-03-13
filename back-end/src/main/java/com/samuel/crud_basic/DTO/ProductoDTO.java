package com.samuel.crud_basic.DTO;

public class ProductoDTO {
    private String nombre;

    private String precio;

    public ProductoDTO(){}

    public ProductoDTO(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    
}
