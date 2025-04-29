package com.samuel.crud_basic.DTO;

public class ProductoDTO {

    private String nombre;
    private String precio;

    // Constructor vacío
    public ProductoDTO() {
    }

    // Constructor con parámetros
    public ProductoDTO(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y setters
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