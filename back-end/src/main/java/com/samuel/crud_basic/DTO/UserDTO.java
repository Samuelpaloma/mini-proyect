package com.samuel.crud_basic.DTO;

public class UserDTO {

    private String nombre;

    private String email;

    private String contrasena;

    private String telefono;

    private String direccion;

    public UserDTO() {} 

    public UserDTO(String nombre, String email, String contrasena, String telefono, String direccion) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    public String getDireccion() {
        return direccion;
    }

}
