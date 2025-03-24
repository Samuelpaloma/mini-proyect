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

    @Column(name = "description", length = 150, nullable = false)
    private String description;

    @Column(name = "precio", nullable = false)
    private String precio;

    // Constructor vacío (necesario para JPA)
    public Menu() {
    }

    // Constructor con parámetros
    public Menu(int id_menu, String name, String description, String precio) {
        this.id_menu = id_menu;
        this.name = name;
        this.description = description;
        this.precio = precio;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
