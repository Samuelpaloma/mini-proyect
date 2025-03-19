package com.samuel.crud_basic.model;

import jakarta.persistence.*;

@Entity(name = "DetallePedido")
public class DetallePedido {

    @Id
    @Column(name = "id_detalle_pedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalle_pedido;

    @Column(name = "cantidad", nullable = false)
    private String cantidad;

    @Column(name = "cargo", nullable = false)
    private String cargo;

    @Column(name = "salario", nullable = false)
    private String salario;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_menu", nullable = false)
    private Menu menu;

    public DetallePedido() {
    }

    public DetallePedido(int id_detalle_pedido, String cantidad, String cargo, String salario, String telefono,
            Pedido pedido, Menu menu) {
        this.id_detalle_pedido = id_detalle_pedido;
        this.cantidad = cantidad;
        this.cargo = cargo;
        this.salario = salario;
        this.telefono = telefono;
        this.pedido = pedido;
        this.menu = menu;
    }

    public int getId_detalle_pedido() {
        return id_detalle_pedido;
    }

    public void setId_detalle_pedido(int id_detalle_pedido) {
        this.id_detalle_pedido = id_detalle_pedido;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
