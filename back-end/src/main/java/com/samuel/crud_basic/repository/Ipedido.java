package com.samuel.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuel.crud_basic.model.Pedido;

public interface  Ipedido extends JpaRepository
<Pedido, Integer> 

{
    
}
    