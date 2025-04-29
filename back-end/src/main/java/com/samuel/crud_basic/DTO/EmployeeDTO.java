package com.samuel.crud_basic.DTO;

public class EmployeeDTO {

    private String name;
    private String cargo;
    private String telefono;

    // Constructor vacío
    public EmployeeDTO() {
    }

    // Constructor con parámetros
    public EmployeeDTO(String name, String cargo, String telefono) {
        this.name = name;
        this.cargo = cargo;
        this.telefono = telefono;
    }

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}