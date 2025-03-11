package com.samuel.crud_basic.model;

import jakarta.persistence.*;

@Entity(name = "DetallePedido")
public class DetallePedido {

    @Id
    @Column(name = "id_detalle_pedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalle_pedido;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    // Relación con Pedido (Muchos detalles pueden estar en un solo pedido)
    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    // Relación con Menu (Un detalle pertenece a un solo menú)
    @ManyToOne
    @JoinColumn(name = "id_menu", nullable = false)
    private Menu menu;

    // Constructor vacío (obligatorio para JPA)
    public DetallePedido() {
    }

    // Constructor con parámetros
    public DetallePedido(int id_detalle_pedido, int cantidad, Pedido pedido, Menu menu) {
        this.id_detalle_pedido = id_detalle_pedido;
        this.cantidad = cantidad;
        this.pedido = pedido;
        this.menu = menu;
    }

    // Getters y Setters
    public int getId_detalle_pedido() {
        return id_detalle_pedido;
    }

    public void setId_detalle_pedido(int id_detalle_pedido) {
        this.id_detalle_pedido = id_detalle_pedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
