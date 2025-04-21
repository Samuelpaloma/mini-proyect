package com.samuel.crud_basic.DTO;

public class MenuDTO {
    private String name;

    private String imagen;
    
    private String precio;

    public MenuDTO(){}

    public MenuDTO(String name, String imagen, String precio) {
        this.name = name;
        this.imagen = imagen;
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

    public String getPrecio(){
        return precio;
    }

    public void setPrecio(String precio){
        this.precio = precio;
    }

    
}
