package com.samuel.crud_basic.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name="pedido")

public class pedido {

    @Id
    @Column(name="id_pedido")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_pedido;

    @Column(name="estado", length=100, nullable=false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private user user;

    @OneToMany
    @JoinColumn(name = "pedido")
    private detalle_pedido detalle_pedido;

    public pedido(int id_pedido, String estado) {
        this.id_pedido = id_pedido;
        this.estado = estado;
    }

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

    


    

    

   
    
}


