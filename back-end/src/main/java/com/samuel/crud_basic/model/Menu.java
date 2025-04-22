package com.samuel.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    private int id_menu;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "imagen", columnDefinition = "LONGTEXT", nullable = false)
    private String imagen;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name="status",nullable=false, columnDefinition = "boolean default true")
    private boolean status;

    // Constructor vacío (necesario para JPA)
    public Menu() {
    }


    public Menu(int id_menu,String imagen, String name, Double precio, boolean status) {
        this.id_menu = id_menu;
        this.imagen = imagen;
        this.name = name;
        this.precio = precio;
        this.status = status;
    }

    // Getters y Setters
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
