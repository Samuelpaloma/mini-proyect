package com.samuel.crud_basic.DTO;

public class MenuDTO {
    private String name;

    private String description;
    
    private String precio;

    public MenuDTO(){}

    public MenuDTO(String name, String description, String precio) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrecio(){
        return precio;
    }

    public void setPrecio(String precio){
        this.precio = precio;
    }

    
}
