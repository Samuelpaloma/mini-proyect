package com.samuel.crud_basic.DTO;

public class MenuDTO {
    private String name;

    private String imagen;
    
    private Double precio;

    public MenuDTO(){}

    public MenuDTO(String name, String imagen, Double precio) {
        this.name = name;
        this.imagen = imagen;
        this.precio = precio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }


    
}
