package com.samuel.crud_basic.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity(name="table")

public class table {

    @Id
    @Column(name="id_table")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_table;

    @Column(name="capacidad", length=100, nullable=false)
    private int capacidad;

    @Column(name = "ubicacion", length = 150, nullable = false)
   private String ubicacion;

   @OneToMany
   @JoinColumn(name = "table")
    private table table;

   public table(int id_table, int capacidad, String ubicacion) {
    this.id_table = id_table;
    this.capacidad = capacidad;
    this.ubicacion = ubicacion;

    
}

public int getId_table() {
    return id_table;
}

public void setId_table(int id_table) {
    this.id_table = id_table;
}

public int getCapacidad() {
    return capacidad;
}

public void setCapacidad(int capacidad) {
    this.capacidad = capacidad;
}

public String getUbicacion() {
    return ubicacion;
}

public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
}



   

    
}
