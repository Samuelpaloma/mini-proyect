package com.samuel.crud_basic.DTO;

public class ProovedorDTO {

    private String name;

    private String telefono;

    private String direccion;

    public ProovedorDTO(){}

    public ProovedorDTO(String name, String telefono, String direccion){
        this.name = name;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getName(){
        return name;
    }

    public void  setName(String name){
        this.name = name;
    }

    public String getTelefono(){
        return telefono;
    }

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public String getDireccion(){
        return direccion;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }



    
}
