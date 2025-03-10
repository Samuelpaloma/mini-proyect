package com.samuel.crud_basic.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="factura")

public class factura {

    @Id
    @Column(name="id_factura")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_factura;

    @Column(name="fecha", length=100, nullable=false)
    private String fecha;

    @Column(name = "total", length = 150, nullable = false)
   private double total;

   @ManyToOne
    @JoinColumn(name = "id_user")
    private user user;

public factura(int id_factura, String fecha, double total, com.samuel.crud_basic.model.user user) {
    this.id_factura = id_factura;
    this.fecha = fecha;
    this.total = total;
    this.user = user;
}

public int getId_factura() {
    return id_factura;
}

public void setId_factura(int id_factura) {
    this.id_factura = id_factura;
}

public String getFecha() {
    return fecha;
}

public void setFecha(String fecha) {
    this.fecha = fecha;
}

public double getTotal() {
    return total;
}

public void setTotal(double total) {
    this.total = total;
}

public user getUser() {
    return user;
}

public void setUser(user user) {
    this.user = user;
}

    








   

   

    
}
