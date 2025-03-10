package com.samuel.crud_basic.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="reservation")

public class reservation {

    @Id
    @Column(name="id_reservation")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_reservation;

    @Column(name="fecha", length=100, nullable=false)
    private String fecha;

    @Column(name = "hora", length = 150, nullable = false)
    private String hora;

     @ManyToOne
    @JoinColumn(name = "id_user")
    private user user;
    
    @ManyToOne
    @JoinColumn(name = "id_table")
    private table table;

    //Constructor

    public reservation(int id_reservation, String fecha, String hora) {
      this.id_reservation = id_reservation;
      this.fecha = fecha;
      this.hora = fecha;
   }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
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

   
    
}
