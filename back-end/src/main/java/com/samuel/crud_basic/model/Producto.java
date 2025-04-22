package com.samuel.crud_basic.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="producto")

public class Producto {

    @Id
    @Column(name="id_producto")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_product;

    @Column(name="nombre", length=100, nullable=false)
    private String nombre;

    @Column(name = "precio", length = 150, nullable = false)
   private String precio;

   public Producto() {
    }

    public Producto(int id_product, String nombre, String precio) {
        this.id_product = id_product;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }



    








   

   

    
}
