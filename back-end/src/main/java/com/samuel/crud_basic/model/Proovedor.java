package com.samuel.crud_basic.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="proovedor")

public class Proovedor {

    @Id
    @Column(name="id_proovedor")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_proovedor;

    @Column(name="name", length=100, nullable=false)
    private String name;

    @Column(name = "telefono", length = 150, nullable = false)
   private String telefono;

   @Column(name = "direccion", length = 150, nullable = false)
   private String direccion;

   public Proovedor() {
}

    public Proovedor(int id_proovedor,String name, String telefono, String direccion) {
        this.id_proovedor = id_proovedor;
        this.name = name;
        this.telefono = telefono;
        this.direccion = direccion;
    }

public int getId_proovedor() {
    return id_proovedor;
}

public void setId_proovedor(int id_proovedor) {
    this.id_proovedor = id_proovedor;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getTelefono() {
    return telefono;
}

public void setTelefono(String telefono) {
    this.telefono = telefono;
}

public String getDireccion() {
    return direccion;
}

public void setDireccion(String direccion) {
    this.direccion = direccion;
}





   

   

    
}
