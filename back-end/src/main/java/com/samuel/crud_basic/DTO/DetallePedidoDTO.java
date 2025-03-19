package com.samuel.crud_basic.DTO;

public class DetallePedidoDTO {
    private String cantidad;

    private String cargo;
    
    private String salario;

    private String telefono;

    public DetallePedidoDTO(){}

    public DetallePedidoDTO(String cantidad, String cargo, String salario, String telefono) {
        this.cantidad = cantidad;
        this.cargo = cargo;
        this.salario = salario;
        this.telefono = telefono;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
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
