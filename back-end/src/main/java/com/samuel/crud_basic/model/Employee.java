package com.samuel.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "employee")
public class Employee {

    @Id
    @Column(name = "id_employee")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_employee;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "cargo", length = 150, nullable = false)
    private String cargo;

    @Column(name = "telefono", length = 150, nullable = false)
    private String telefono;

    // Constructor vacío (requerido por Hibernate)
    public Employee() {
    }

    // Constructor con parámetros
    public Employee(int id_employee, String name, String cargo, String telefono) {
        this.id_employee = id_employee;
        this.name = name;
        this.cargo = cargo;
        this.telefono = telefono;
    }

    // Getters y setters
    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}