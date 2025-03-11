package com.samuel.crud_basic.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="factura")
public class Factura {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_factura")
    private int idFactura;

    @Column(name="fecha", nullable=false)
    private LocalDate fecha; // Se cambió a LocalDate

    @Column(name="total", nullable=false)
    private double total;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user; // Se cambió a User con mayúscula

    // Constructor vacío requerido por JPA
    public Factura() {
    }

    // Constructor con parámetros
    public Factura(int idFactura, LocalDate fecha, double total, User user) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.total = total;
        this.user = user;
    }

    // Getters y Setters
    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
