package com.samuel.crud_basic.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="detalle_pedido")

public class detalle_pedido {

    @Id
    @Column(name="id_detalle_pedido")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_detalle_pedido;

    @Column(name="cantidad", length=100, nullable=false)
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private pedido pedido;
    
    @ManyToOne
    @JoinColumn(name = "id_menu")
    private menu menu;

    public detalle_pedido(int id_detalle_pedido, int cantidad, com.samuel.crud_basic.model.pedido pedido,
            com.samuel.crud_basic.model.menu menu) {
        this.id_detalle_pedido = id_detalle_pedido;
        this.cantidad = cantidad;
        this.pedido = pedido;
        this.menu = menu;
    }

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

    public pedido getPedido() {
        return pedido;
    }

    public void setPedido(pedido pedido) {
        this.pedido = pedido;
    }

    public menu getMenu() {
        return menu;
    }

    public void setMenu(menu menu) {
        this.menu = menu;
    }


    




    


    

    

   
    
}


