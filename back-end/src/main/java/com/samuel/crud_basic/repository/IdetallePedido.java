package com.samuel.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuel.crud_basic.model.DetallePedido;

public interface  IdetallePedido extends JpaRepository
<DetallePedido, Integer> 

{
    
}