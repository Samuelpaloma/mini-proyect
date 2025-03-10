package com.samuel.crud_basic.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity(name="user")

public class user {

    @Id
    @Column(name="id_user")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_user;

    @Column(name="name", length=100, nullable=false)
    private String name;

    @Column(name = "email", length = 150, nullable = false)
   private String email;

    @Column(name = "contrasena", length = 150, nullable = false)
    private String contrasena;

    @Column(name = "telefono", length = 150, nullable = false)
    private int telefono;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @OneToMany
    @JoinColumn(name = "user")
    private reservation reservation;

    //Constructor

    public user(int id_user, String name, String email, String contrasena, int telefono,
         String direccion) {
      this.id_user = id_user;
      this.name = name;
      this.email = email;
      this.contrasena = contrasena;
      this.telefono = telefono;
      this.direccion = direccion;
   }

   // get del ID
   public int getId_user() {
      return id_user;
   }

   // set del ID
   public void setId_user(int id_user) {
         this.id_user = id_user;
   }

   // get del firstName
   public String get_nombre() {
      return name;
   }

   // set del firstName
   public void set_breedName(String name) {
      this.name = name;
   }

   // get del phone
   public String get_contrasena() {
      return contrasena;
   }

   // set del phone
   public void set_contrasena(String contrasena) {
      this.contrasena = contrasena;
   }

   public int get_telefono() {
      return telefono;
   }

   // set del phone
   public void set_telefono(int telefono) {
      this.telefono = telefono;
   }

   public String get_direccion() {
      return direccion;
   }

   // set del phone
   public void set_direccion(String direccion) {
      this.direccion = direccion;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
    
}
