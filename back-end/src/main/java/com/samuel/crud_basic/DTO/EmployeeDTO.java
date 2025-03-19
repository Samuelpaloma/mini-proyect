package com.samuel.crud_basic.DTO;

public class EmployeeDTO {
    private String name;

    private String cargo;
    
    private String salario;

    private String telefono;

    public EmployeeDTO(){}

    public EmployeeDTO(String name, String cargo, String salario, String telefono) {
        this.name = name;
        this.cargo = cargo;
        this.salario = salario;
        this.telefono = telefono;
    }

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

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    

    
}
