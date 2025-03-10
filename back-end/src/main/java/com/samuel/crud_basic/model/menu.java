package com.samuel.crud_basic.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="menu")

public class menu {

    @Id
    @Column(name="id_menu")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_menu;

    @Column(name="name", length=100, nullable=false)
    private String name;

    @Column(name = "description", length = 150, nullable = false)
    private String description;

    @Column(name = "precio", length = 150, nullable = false)
    private double precio;

    public menu(int id_menu, String name, String description, double precio) {
        this.id_menu = id_menu;
        this.name = name;
        this.description = description;
        this.precio = precio;
    }

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    

   
    
}


