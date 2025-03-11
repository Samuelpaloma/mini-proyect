package com.samuel.crud_basic.model;

import java.util.List;

import org.apache.catalina.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "Pedido")
public class Pedido {

    @Id
    @Column(name = "id_pedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pedido;

    @Column(name = "estado", length = 100, nullable = false)
    private String estado;

    // Relación con User (Muchos pedidos pueden pertenecer a un usuario)
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    // Relación con DetallePedido (Un pedido puede tener varios detalles)
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetallePedido> detallePedido;

    // Constructor vacío (Obligatorio para JPA)
    public Pedido() {
    }

    // Constructor con parámetros
    public Pedido(int id_pedido, String estado, User user, List<DetallePedido> detallePedido) {
        this.id_pedido = id_pedido;
        this.estado = estado;
        this.user = user;
        this.detallePedido = detallePedido;
    }

    // Getters y Setters
    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<DetallePedido> getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(List<DetallePedido> detallePedido) {
        this.detallePedido = detallePedido;
    }
}
