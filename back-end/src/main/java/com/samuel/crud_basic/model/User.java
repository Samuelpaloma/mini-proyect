package com.samuel.crud_basic.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int idUser;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;

    @Column(name = "contrasena", length = 150, nullable = false)
    private String contrasena;

    @Column(name = "telefono", length = 15, nullable = false)
    private String telefono;  // Cambiado a String

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    public User() {
    }

    public User( int idUser, String nombre,  String email, String contrasena, String telefono, String direccion) {
                this.idUser = idUser;
                this.nombre = nombre;
                this.email = email;
                this.contrasena = contrasena;
                this.telefono = telefono;
                this.direccion = direccion;
    }

    // Getters y Setters
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(final int idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(final String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(final String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(final String direccion) {
        this.direccion = direccion;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(final List<Reservation> reservations) {
        this.reservations = reservations;
    }
}