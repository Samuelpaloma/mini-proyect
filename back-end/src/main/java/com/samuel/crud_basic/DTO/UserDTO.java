package com.samuel.crud_basic.DTO;

public class UserDTO {

    private String name;
    private String email;
    private String password;

    // Constructor vacío
    public UserDTO() {
    }

    // Constructor con parámetros
    public UserDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}