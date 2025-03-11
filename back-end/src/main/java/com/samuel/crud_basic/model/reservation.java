package com.samuel.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private int idReservation;

    @Column(name = "fecha", length = 100, nullable = false)
    private String fecha;

    @Column(name = "hora", length = 150, nullable = false)
    private String hora;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_mesa", nullable = false)  // Se cambió "table" por "mesa"
    private Mesa mesa;

    // Constructor vacío (requerido por JPA)
    public Reservation() {
    }

    // Constructor con parámetros
    public Reservation(int idReservation, String fecha, String hora, User user, Mesa mesa) {
        this.idReservation = idReservation;
        this.fecha = fecha;
        this.hora = hora;
        this.user = user;
        this.mesa = mesa;
    }

    // Getters y Setters
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
}
