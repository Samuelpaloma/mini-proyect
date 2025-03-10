package com.samuel.crud_basic.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="employee")

public class employee {

    @Id
    @Column(name="id_employee")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_employee;

    @Column(name="name", length=100, nullable=false)
    private String name;

    @Column(name = "cargo", length = 150, nullable = false)
   private String cargo;

    @Column(name = "salario", length = 150, nullable = false)
    private int salario;

    @Column(name = "telefono", length = 150, nullable = false)
    private int telefono;


    //Constructor

    public employee(int id_employee, String name, String cargo, int salario, int telefono) {
      this.id_employee = id_employee;
      this.name = name;
      this.cargo = cargo;
      this.salario = salario;
      this.telefono = telefono;
      
   }

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

    public int getSalario() {
      return salario;
    }

    public void setSalario(int salario) {
      this.salario = salario;
    }

    public int getTelefono() {
      return telefono;
    }

    public void setTelefono(int telefono) {
      this.telefono = telefono;
    }

    
}
